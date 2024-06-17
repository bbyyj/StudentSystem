// mock数据模拟
import Mock from 'mockjs'
// 导入数据
import pieData from '../../data/mockData/pieData'
import tableData from '../../data/mockData/tableData'

// 图表数据
let List =[]
// 直接导出
export default {
    getStatisticalData: () => {

        //Mock.Random.float 产生随机数100到8000之间 保留小数 最小0位 最大0位
        for (let i = 0; i < 7; i++) {
            List.push(
                Mock.mock({
                    "2020级": Mock.Random.float(60, 100, 0, 0),
                    "2021级": Mock.Random.float(60, 100, 0, 0),
                    "2022级": Mock.Random.float(60, 100, 0, 0),
                    "2023级": Mock.Random.float(60, 100, 0, 0),
                })
            )
        }
        // 返回给浏览器的数据
        return {
            code: 200,
            data: {
                // 饼图
                pieData: pieData,
                // 柱状图
                barData:{
                    date:['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                    data:{
                        '2020级': [320, 302, 301, 334, 390, 330, 320],
                        '2021级': [220, 182, 191, 234, 290, 330, 310],
                        '2022级': [220, 182, 191, 234, 290, 330, 310],
                        '2023级': [150, 232, 201, 154, 190, 330, 410]},
                },
                // 折线图
                orderData: {
                    date: ['2022上学期', '2022下学期', '2023上学期', '2023下学期'],
                    data: List
                },
                tableData,
                value:[1234,210,123],
            }
        }
    }
}
