
<template>
    <el-upload
      class="upload-demo"
      action="#"
      :http-request="upload"
      :on-success="handleSuccess"

      :file-list="innerFileList"
      list-type="text"
      :limit="1"
      :before-upload="beforeUpload"
    >
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">请上传PDF、JPEG 和 PNG格式的附件</div>
    </el-upload>
  </template>
  
  <script>
  const COS = require('cos-js-sdk-v5')
  // 填写自己腾讯云cos中的key和id (密钥)
  const cos = new COS({
      SecretId: 'AKIDRqKoL1paCkhcSbkdnw3Me3VuzANkFiIs', // 身份识别ID
      SecretKey: '2N9kHnHiRNMLG8BEFCBKazUSXdcglmrx' // 身份密钥
  })

  export default {
    name: "CustomUpload",
    props: {
      value: {
        type: String,
        default: "",
      },
      acceptedTypes: {
        // 定义接受的文件类型数组
        type: Array,
        default: () => ["application/pdf", "image/jpeg", "image/png"], // 设置3种格式
      },
    },
    data() {
      return {
        innerFileList: this.value ? [{ name: "已上传", url: this.value }] : [],
          imageUrl:"", // 返回的图片地址
      };
    },
    watch: {
      value(val) {
        this.innerFileList = val ? [{ name: "已上传", url: val }] : [];
      },
    },
    methods: {
        // 上传之前
      beforeUpload(file) {
        const fileType = file.type;
        if (!this.acceptedTypes.includes(fileType)) {
          this.$message.error("文件类型不正确");
          return false; // 如果文件类型不匹配则阻止上传
        }
        return true; // 否则允许上传
      },
      handleSuccess(response, file) {
        const fileUrl = response.url; // 假设服务端返回的是单个字符串URL
        this.$emit("input", fileUrl);
        this.innerFileList = [{ name: file.name, url: fileUrl }];
      },
        // 上传事件
        upload(res) {
            if (res.file) {
                // 执行上传操作
                cos.putObject({
                    Bucket: 'attachment-1325509405', /* 存储桶 */
                    Region: 'ap-guangzhou', /* 存储桶所在地域，必须字段 */
                    Key: res.file.name, /* 文件名 */
                    StorageClass: 'STANDARD', // 上传模式, 标准模式
                    Body: res.file // 上传文件对象
                }, (err, data) => {
                    console.log(err || data)
                    // 上传成功之后
                    if (data.statusCode === 200) {
                        this.imageUrl = `https://${data.Location}`
                        console.log("this.imageUrl:",this.imageUrl)
                        this.$emit("input", this.imageUrl); // 传递给父组件
                    }
                })
            }
        }
    },
  };
  </script>
  
  <style>
  .upload-demo .el-upload__tip {
    font-size: 12px;
  }
  .upload-demo .el-upload__input {
    display: none;
  }
  </style>