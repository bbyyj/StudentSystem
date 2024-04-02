<template>
    <el-upload
      class="upload-demo"
      action="https://your-upload-endpoint.com/upload"
      :on-success="handleSuccess"
      :file-list="innerFileList"
      list-type="text"
      :limit="1"
      :before-upload="beforeUpload"
    >
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">仅限特定文件类型上传</div>
    </el-upload>
  </template>
  
  <script>
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
        default: () => ["application/pdf", "image/jpeg", "image/png"],
      },
    },
    data() {
      return {
        innerFileList: this.value ? [{ name: "已上传", url: this.value }] : [],
      };
    },
    watch: {
      value(val) {
        this.innerFileList = val ? [{ name: "已上传", url: val }] : [];
      },
    },
    methods: {
      beforeUpload(file) {
        const fileType = file.type;
        if (!this.acceptedTypes.includes(fileType)) {
          this.$message.error("文件类型不正确");
          return false; // 如果文件类型不匹配则阻止上传
        }
        return true; // 否则允许上传
      },
      handleSuccess(response, file, fileList) {
        const fileUrl = response.url; // 假设服务端返回的是单个字符串URL
        this.$emit("input", fileUrl);
        this.innerFileList = [{ name: file.name, url: fileUrl }];
      },
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