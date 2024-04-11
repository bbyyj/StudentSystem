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
        }else if(name === 'patent'){
            return {
                code: 200,
                data: {
                    tableData: [{
                        id: '12987125',

                        studentName: '发明人姓名',
                        studentGrade: '发明人年级',
                        studentID: '发明人学号',
                        studentClass: '发明人班级',

                        patentName: '专利名称',
                        applicationNumber: '专利申请号',
                        certificateNumber: '专利证书编号',
                        inventors: '全部发明人',
                        patentType: '专利类型',
                        isAccepted: '是否已受理',
                        acceptanceDate: '受理时间',
                        isPublished: '是否已公开',
                        publicationDate: '公开时间',
                        isGranted: '是否已授权',
                        grantDate: '授权时间',
                        isTransferred: '是否已转让',
                        transferDate: '转让时间',
                        transferIncome: '转让收入',
                        remarks: '备注',

                        submitTime: '2019-09-09',
                        submitState: '已审核',
                        textMessage: '附件',
                    }],
                    total: 1
                },
                message: '获取成功'
            }
        }else if(name === 'software') {
            return {
                code: 200,
                data: {
                    tableData: [{
                        id: '12987125',
                        studentName: '著作人姓名',
                        studentGrade: '著作人年级',
                        studentID: '著作人学号',
                        studentClass: '著作人班级',

                        softwareCopyrightName: '软件著作权名称',
                        inventorOrder: '第几发明人',
                        allAuthors: '全部完成人',
                        applicationStatus: '申请状态',
                        statusDate: '该状态时间',
                        remarks: '备注',

                        submitTime: '2019-09-09',
                        submitState: '已审核',
                        textMessage: '附件',
                    }],
                    total: 1
                },
                message: '获取成功'
            }
        }else if(name === 'monograph') {
            return {
                code: 200,
                data: {
                    tableData: [{
                        id: '12987125',

                        studentName: '发表人姓名',
                        studentGrade: '发表人年级',
                        studentID: '发表人学号',
                        studentClass: '发表人班级',

                        monographName: '专著名称',
                        authorOrder: '第几作者',
                        allAuthors: '该著作全部作者',
                        publishingUnit: '出版单位',
                        bookCategory: '著作类别',
                        ISBN: 'ISBN',
                        publicationDate: '出版时间',
                        remarks: '备注',

                        submitTime: '2019-09-09',
                        submitState: '已审核',
                        textMessage: '附件',
                    }],
                    total: 1
                },
                message: '获取成功'
            }
        }else if(name === 'exchange'){
            return {
                code: 200,
                data: {
                    tableData: [{
                        id: '12987125',
                        studentName: '参与人姓名',
                        studentGrade: '参与人年级',
                        studentID: '参与人学号',
                        studentClass: '参与人班级',

                        projectName: '项目名称',
                        reasonType: '事由类型',
                        fundingSource: '经费来源',
                        destinationCountry: '前往国家/地区',
                        destinationCity: '前往城市',
                        destinationInstitution: '前往院校/机构名称或竞赛/学术会议名称',
                        projectDuration: '项目期限',
                        departureDate: '出发时间',
                        returnDate: '返校时间',
                        currentStatus: '目前状态',
                        isDeclarationComplete: '是否已完成申报/报备',
                        isPredepartureEducationCompleted: '是否已参加行前教育',
                        isAgreementSigned: '是否已签订《学生赴校外交流学习承诺书》《学生声明》《亲属声明》',
                        isConversationCompleted: '是否已开展谈心谈话',
                        remarks: '备注',
                        submitTime: '2019-09-09',
                        submitState: '已审核',
                        textMessage: '附件',
                    }],
                    total: 1
                },
                message: '获取成功'
            }
        }else if(name === 'volunteer'){
            return {
                code: 200,
                data: {
                    tableData: [{
                        id: '12987125',

                        studentName: '参与人姓名',
                        studentGrade: '参与人年级',
                        studentID: '参与人学号',
                        studentClass: '参与人班级',

                        volunteerOrganization: '志愿组织团体',
                        volunteerActivityName: '志愿活动名称',
                        volunteerActivityType: '志愿活动类型',
                        volunteerStartDate: '参与志愿活动开始时间',
                        volunteerEndDate: '参与志愿活动结束时间',
                        volunteerServiceHours: '志愿服务时长',

                        submitTime: '2019-09-09',
                        submitState: '已审核',
                        textMessage: '附件',
                    }],
                    total: 1
                },
                message: '获取成功'
            }
        } else {
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