<template>
    <el-row>
        <el-col :span="6">
            <!-- user卡片 -->
            <el-card>
                <div class="user">
                    <img src="../assets/images/admin.jpeg" alt="">
                    <div class="userInfo">
                        <p class="name">{{name}}</p>
                        <p class="access">{{identity}}</p>
                    </div>
                </div>
                <div class="loginInfo">
                    <p>上次登录时间：<span>{{loginTime}}</span></p>
                    <p>上次登陆地点：<span>{{loginPlace}}</span></p>
                </div>
            </el-card>

          <div class="items">
            <el-card v-for="(item,index) in CountData" :key="item.name" :body-style="{ display: 'flex', padding: 0 }">
              <i class="icon" :class="`el-icon-${item.icon}`" :style="{ backgroundColor: item.color }"></i>
              <div class="details">
                <p class="num">{{ value[index] }}</p>
                <p class="desc">{{ item.name }}</p>
              </div>
            </el-card>
          </div>
        </el-col>
        <el-col :span="18">
            <!-- echarts图表 -->
            <div style="margin-left:20px">
                <!-- 折线图 -->
                <el-card style="height:300px">
                    <div ref="line" style="height:290px"></div>
                </el-card>

                <div class="graph">
                    <!-- 柱状图 -->
                    <el-card style="height:300px">
                        <div ref="bar" style="height:280px"></div>
                    </el-card>
                    <!-- 饼状图 -->
                    <el-card style="height:300px">
                        <div ref="pie" style="height:280px"></div>
                    </el-card>
                </div>

            </div>
        </el-col>
    </el-row>
</template>

<script>
import CountData from '../data/CountData'
import * as echarts from 'echarts'

import request from "@/utils/request";

export default {
  data() {
    return {
      CountData,

      loginTime: "2024-6-17",
      loginPlace: "广州",
      identity: "辅导员",
      name: "Admin",

      value: [],
    }
  },
  mounted() {
    this.initFirst()
  },
  methods: {
    initFirst(){  // 初始化各种图表
      request.get("/home/getData").then(res => {
        if (res.data.getStatisticalData.code === 200) {
          this.initPie(res.data.getStatisticalData.data.pieData);
          this.initLine(res.data.getStatisticalData.data.orderData);
          this.initBar(res.data.getStatisticalData.data.barData);

          this.value = res.data.getStatisticalData.data.value;
        } else {
          this.$message.error("可视数据初始化错误！");
        }
      })
    },
    initPie(data){
      let myChart = echarts.init(this.$refs.pie);
      let option;

      option = {
        title: {
          text: '综测分数分布',
          // subtext: 'Fake Data',
          left: 'center',
        },
        tooltip: {  // 悬停弹窗
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)',
          position: ['58%', '75%']
        },
        legend: {  // 侧边数据标明
          orient: 'horizontal',
          bottom: 0,
        },
        toolbox: {
          show: true,
          feature: {
            dataView: { show: true, readOnly: true },
            saveAsImage: { show: true }
          }
        },
        series: [
          {
            name: '具体人数',
            type: 'pie',
            radius: [30, 100],
            data:data,
            roseType: 'radius',
            itemStyle: {
              borderRadius: 5
            },
            emphasis: { // 选中高亮样式
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            label: {
              position: 'outer',
              alignTo: 'edge',
              margin: 10
            },
          }
        ]
      };

      option && myChart.setOption(option);  // 设置饼图
    },
    initLine(order){
      let myChart = echarts.init(this.$refs.line);
      let option;

      const xAxis = Object.keys(order.data[0]);
      option = {
        title: {
          text: '各年级平均分数'
        },
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '8%',
          right: '8%',
          bottom: '15%',
          containLabel: true
        },
        legend: {
          orient: 'horizontal',
          data:[]
        },
        toolbox: {
          show: true,
          feature: {
            dataView: { show: true, readOnly: true },
            saveAsImage: { show: true }
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: order.date
        },
        yAxis: {
          type: 'value'
        },
        series: []
      };
      xAxis.forEach(key => {
        option.legend.data.push(key);

        option.series.push({
          name: key,
          type: 'line',
          // key对应的orderData的所有值
          data: order.data.map(item => item[key])
        })
      });

      const yValues = order.data.flatMap(item => xAxis.map(key => item[key]));
      const yMin = Math.min(...yValues) - 10; // 数据的最小值减去一些余量
      const yMax = Math.max(...yValues) + 10; // 数据的最大值加上一些余量

      option.yAxis.min = yMin;
      option.yAxis.max = yMax;
      option && myChart.setOption(option);  // 设置饼图
    },
    initBar(data){
      let myChart = echarts.init(this.$refs.bar);
      let option;

      option = {
        title: {
          text: '每日资料提交情况'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow' // 'shadow' as default; can also be 'line' or 'shadow'
          }
        },
        legend: {
          top: '10%',
        },
        toolbox: {
          show: true,
          feature: {
            dataView: { show: true, readOnly: true },
            saveAsImage: { show: true }
          }
        },
        grid: {
          left: '3%',
          right: '5%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
        },
        yAxis: {
          type: 'category',
          data: data.date
        },
        series: Object.keys(data.data).map(grade => ({
          name: grade,
          type: 'bar',
          stack: 'total',
          label: {
            show: true
          },
          emphasis: {
            focus: 'series'
          },
          data: data.data[grade] // 设置每个系列的数据
        }))
      };
      option && myChart.setOption(option);  // 设置饼图
    }

  },

}
</script>

<style lang="less" scoped>
.user {
    // 垂直居中
    display: flex;
    align-items: center;

    margin-bottom: 20px;    // 分割线距离loginInfo的距离

    padding-bottom: 20px;    // 分割线距离User的距离
    border-bottom: 1px solid #ccc;

    img {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        margin-right: 40px;
    }

    .userInfo {
        .name {
            font-size: 28px;
            margin-bottom: 10px;
        }

        .access {
            color: #999999;
        }
    }
}

.loginInfo {
    p {
        line-height: 28px;
        font-size: 14px;
        color: #999999;

        span {
            color: #666666;
            margin-left: 20px;
        }
    }
}

.items {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;

    .el-card {
        width: 100%;
        margin-top: 30px;

        .icon {
            width: 80px;
            height: 80px;
            line-height: 80px;
            text-align: center;
            font-size: 30px;
            color: #fff;
        }

        .details {
            // 竖着排且居中
            display: flex;
            flex-direction: column;
            justify-content: center;

            margin-left: 15px;

            .num {
                font-size: 24px;
                margin-bottom: 10px;
                line-height: 30px;
                height: 30px;
            }

            .desc {
                font-size: 14px;
                color: #999;
                text-align: center;
            }
        }
    }
}

.graph {
    display: flex;
    // 两个靠边
    justify-content: space-between;
    margin-top: 20px;

    .el-card {
        width: 49%;
    }
}
</style>