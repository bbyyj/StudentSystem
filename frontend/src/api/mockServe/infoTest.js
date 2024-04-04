export default {
    getLoadingData: config => {
        const name  = JSON.parse(config.body).routeName;
        // 后端加上筛选逻辑
        if(name === 'competition'){
            return {
                code: 200,
                data: {
                    tableData: [{
                        id: '12987125',
                        studentName: '好滋好味鸡蛋仔',
                        studentGrade: '2019级',
                        studentClass: '获奖人班级',
                        studentID: "11111",
                        achievmentType: '成果类别',
                        achivementTime: '获得时间，2019-09-09',
                        competitionName: '比赛名称',
                        achievementName: '成果名称，选填',
                        awardName: '获奖名称',
                        achievementGrade: '成果级别',
                        achievementRank: '成果等级',
                        organizationUnit:'评奖组织单位',
                        otherPaticipant:'其他参与人员',

                        submitTime: '2019-09-09',
                        submitState: '已审核',
                        textMessage: '附件',
                    },{
                        id: '12987125',
                        studentName: '好滋好味鸡蛋仔',
                        studentGrade: '2019级',
                        studentClass: '获奖人班级',
                        studentID: "11111",
                        achievmentType: '成果类别',
                        achivementTime: '获得时间，2019-09-09',
                        competitionName: '比赛名称',
                        achievementName: '成果名称，选填',
                        awardName: '获奖名称',
                        achievementGrade: '成果级别',
                        achievementRank: '成果等级',
                        organizationUnit:'评奖组织单位',
                        otherPaticipant:'其他参与人员',

                        submitTime: '2019-02-03',
                        submitState: '待审核',
                        textMessage: '',
                    },{
                        id: '12987125',
                        studentName: '好滋好味鸡蛋仔',
                        studentGrade: '2019级',
                        studentClass: '获奖人班级',
                        studentID: "11111",
                        achievmentType: '成果类别',
                        achivementTime: '获得时间，2019-09-09',
                        competitionName: '比赛名称',
                        achievementName: '成果名称，选填',
                        awardName: '获奖名称',
                        achievementGrade: '成果级别',
                        achievementRank: '成果等级',
                        organizationUnit:'评奖组织单位',
                        otherPaticipant:'其他参与人员',

                        submitTime: '2019-02-11',
                        submitState: '待审核',
                        textMessage: '',
                    },{
                        id: '12987125',
                        studentName: '好滋好味鸡蛋仔',
                        studentGrade: '2019级',
                        studentClass: '获奖人班级',
                        studentID: "11111",
                        achievmentType: '成果类别',
                        achivementTime: '获得时间，2019-09-09',
                        competitionName: '比赛名称',
                        achievementName: '成果名称，选填',
                        awardName: '获奖名称',
                        achievementGrade: '成果级别',
                        achievementRank: '成果等级',
                        organizationUnit:'评奖组织单位',
                        otherPaticipant:'其他参与人员',

                        submitTime: '2019-09-09',
                        submitState: '已审核',
                        textMessage: '附件',
                    },{
                        id: '12987125',
                        studentName: '好滋好味鸡蛋仔',
                        studentGrade: '2019级',
                        studentClass: '获奖人班级',
                        studentID: "11111",
                        achievmentType: '成果类别',
                        achivementTime: '获得时间，2019-09-09',
                        competitionName: '比赛名称',
                        achievementName: '成果名称，选填',
                        awardName: '获奖名称',
                        achievementGrade: '成果级别',
                        achievementRank: '成果等级',
                        organizationUnit:'评奖组织单位',
                        otherPaticipant:'其他参与人员',

                        submitTime: '2019-02-03',
                        submitState: '待审核',
                        textMessage: '',
                    },{
                        id: '12987125',
                        studentName: '好滋好味鸡蛋仔',
                        studentGrade: '2019级',
                        studentClass: '获奖人班级',
                        studentID: "11111",
                        achievmentType: '成果类别',
                        achivementTime: '获得时间，2019-09-09',
                        competitionName: '比赛名称',
                        achievementName: '成果名称，选填',
                        awardName: '获奖名称',
                        achievementGrade: '成果级别',
                        achievementRank: '成果等级',
                        organizationUnit:'评奖组织单位',
                        otherPaticipant:'其他参与人员',

                        submitTime: '2019-02-11',
                        submitState: '待审核',
                        textMessage: '',
                    }],
                    total: 6
                },
                message: '获取成功'
            }
        }else if(name === 'paper'){
            return {
                code: 200,
                data: {
                    tableData: [{
                        id: '12987125',
                        studentName: '发表人姓名',
                        studentID: '发表人学号',
                        studentGrade: '2019级',
                        studentClass: '获奖人班级',
                        authorType: '作者类型',
                        allAuthorName: '全部作者姓名',
                        corresAuthorName: '通讯作者姓名',
                        journalName: '期刊或会议名称',
                        issnOrCn: 'ISSN/CN',
                        factoring: '影响因子',
                        bestAward: '是否有最佳论文奖',
                        publishStatus: '发表状态',
                        contributeTime: '投稿时间',
                        acceptTime: '接收时间',
                        publishTime: '发表时间',
                        pageRange: '页码范围',
                        doiOrPmId: 'DOI/PMID',
                        articles: '论文类别',
                        ccfRecommend: 'CCF推荐情况',
                        casPartition: '中科院分区',
                        recordStatus: '期刊收录情况',
                        publisher: '出版单位',
                        languange: '语言',
                        interCooperate: '是否国际合作论文',
                        unitCooperate: '是否本单位合作论文',
                        remark: '备注',

                        submitTime: '2019-09-09',
                        submitState: '已审核',
                        textMessage: '附件',
                    }],
                    total: 1
                },
                message: '获取成功'
            }
        }else {
            return {
                code: -999,
                data: {
                    message: '错误',
                    name: name
                }
            }
        }
    }
}