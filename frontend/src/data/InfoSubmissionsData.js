const InfoSubmissionsData = [
    {
    // 比赛获奖相关数据
    items: [
        { fieldName: "type", controlType: "select", label: "成果类别" },
        { fieldName: "time", controlType: "datepicker", label: "获得时间" },
        { fieldName: "competition_name", controlType: "input", label: "比赛名称", message:'请严格按照比赛通知、奖状信息填写，含比赛年份、届别、地区等信息' },
        { fieldName: "award_name", controlType: "input", label: "成果名称" },
        { fieldName: "result_name", controlType: "input", label: "获奖名称", message: '请严格按照奖状信息填写，注明地区、组别等信息' },
        { fieldName: "result_type", controlType: "select", label: "成果级别" },
        { fieldName: "result_level", controlType: "input", label: "成果等级", message: '学科竞赛、艺术比赛获奖等级指特等奖、一等奖、二等奖、三等奖、冠军、亚军、季军、金奖、银奖、铜奖；体育比赛获奖等级指第一名至第八名，冠军、金牌等同于第一名，亚军、银牌等同于第二名，季军、铜牌等同于第三名' },
        { fieldName: "organization", controlType: "input", label: "评奖组织单位", message: '请严格按照比赛通知、奖状信息填写' },
        { fieldName: "teammate", controlType: "input", label: "其他参与人员", message: '姓名+学院，没有就填“无”' }
    ],
    selectOptions: {
        type: [
            { value: '学科竞赛', label: '学科竞赛' },
            { value: '艺术比赛', label: '艺术比赛' },
            { value: '体育比赛', label: '体育比赛' },
            { value: '其他', label: '其他' }
        ],
        result_type: [
            { value: '国际级', label: '国际级' },
            { value: '国家级', label: '国家级' },
            { value: '省部级', label: '省部级' },
            { value: '校级', label: '校级' },
        ],
    },
    rules: {
        type: [
            { required: true, message: '请选择成果类别', trigger: 'change' }
        ],
        time: [
            { required: true, message: '请选择获得时间', trigger: 'change' }
        ],
        competition_name: [
            { required: true, message: '请填写比赛名称', trigger: 'change' }
        ],
        award_name: [
            { required: false, message: '选填', trigger: 'change' }
        ],
        result_name: [
            { required: true, message: '请填写获奖名称', trigger: 'change' }
        ],
        result_type: [
            { required: true, message: '请选择成果类别', trigger: 'change' }
        ],
        result_level: [
            { required: true, message: '请填写成果等级', trigger: 'change' }
        ],
        organization: [
            { required: true, message: '请填写评奖组织单位', trigger: 'change' }
        ],
        teammate: [
            { required: true, message: '请填写其他参与人员', trigger: 'change' }
        ],
        file: [
            { required: true, message: '请上传文件', trigger: 'change' }
        ]
    },
    },
    {
        // 论文发表相关数据
        items: [
            { fieldName: "title", controlType: "input", label: "论文标题" },
            { fieldName: "author_type", controlType: "select", label: "作者类型" },
            { fieldName: "authors", controlType: "input", label: "全部作者姓名", message: '请按署名顺序完整填写中文名' },
            { fieldName: "cor_author", controlType: "input", label: "通讯作者" },
            { fieldName: "jc_name", controlType: "input", label: "发表期刊或会议", message: '请填写全称' },
            { fieldName: "issn_cn", controlType: "input", label: "ISSN/CN" },
            { fieldName: "impact_factor", controlType: "input", label: "影响因子" },
            { fieldName: "is_award", controlType: "input", label: "是否有最佳论文奖" },
            { fieldName: "pub_status", controlType: "select", label: "发表状态" },
            { fieldName: "sub_date", controlType: "datepicker", label: "投稿日期" },
            { fieldName: "acc_date", controlType: "datepicker", label: "接收日期" },
            { fieldName: "pub_date", controlType: "datepicker", label: "发表日期" },
            { fieldName: "page", controlType: "input", label: "卷（期）/页码范围", message: '如未正式发表请留空' },
            { fieldName: "doi_pmid", controlType: "input", label: "DOI/PMID" },
            { fieldName: "paper_type", controlType: "select", label: "论文类型" },
            { fieldName: "ccf_level", controlType: "select", label: "CCF推荐情况" },
            { fieldName: "sci_level", controlType: "select", label: "中科院分区" },
            { fieldName: "include", controlType: "select", label: "期刊收录情况" },
            { fieldName: "publisher", controlType: "input", label: "出版单位" },
            { fieldName: "language", controlType: "input", label: "论文语言" },
            { fieldName: "is_internation", controlType: "select", label: "是否国际合作论文" },
            { fieldName: "is_institution", controlType: "select", label: "是否本单位合作论文" },
            { fieldName: "remark", controlType: "input", label: "备注", message: '需要特别说明的情况，如“最佳论文奖”等' },
        ],
        selectOptions: {
            author_type: [
                { value: 'communication', label: '通讯' },
                { value: 'first_author', label: '一作' },
                { value: 'communication_first_author', label: '通讯+一作' },
                { value: 'communication_shared_first_author', label: '通讯+共同一作' },
                { value: 'shared_first_author', label: '共同一作' },
                { value: 'second_author', label: '二作' },
                { value: 'other', label: '其他' }
            ],
            pub_status: [
                { value: 'submitted', label: '已投稿' },
                { value: 'accepted', label: '已接收' },
                { value: 'published', label: '正式发表' }
            ],
            paper_type: [
                { value: 'conference_paper', label: '会议论文' },
                { value: 'journal_paper', label: '期刊论文' }
            ],
            ccf_level: [
                { value: 'CCF-A', label: 'CCF-A' },
                { value: 'CCF-B', label: 'CCF-B' },
                { value: 'CCF-C', label: 'CCF-C' },
                { value: 'other', label: '其他' }
            ],
            sci_level: [
                { value: 'first_zone', label: '一区' },
                { value: 'second_zone', label: '二区' },
                { value: 'third_zone', label: '三区' },
                { value: 'not_applicable', label: '不适用' }
            ],
            include: [
                { value: 'CSSCI', label: 'CSSCI' },
                { value: 'CSCD', label: 'CSCD' },
                { value: 'SCI', label: 'SCI' },
                { value: 'SSCI', label: 'SSCI' },
                { value: 'EI', label: 'EI' },
                { value: 'A&HCI', label: 'A&HCI' },
                { value: 'other', label: '其他' }
            ],
            is_internation: [
                { value: 'international', label: '国外合作' },
                { value: 'hongkong_macau_taiwan', label: '港澳台合作' },
                { value: 'not_international', label: '非国际合作' }
            ],
            is_institution: [
                { value: 'campus', label: '校内合作' },
                { value: 'college', label: '院内合作' },
                { value: 'no', label: '否' }
            ]
        },
        rules: {
            title: [
                { required: true, message: '请填写论文标题', trigger: 'change' }
            ],
            author_type: [
                { required: true, message: '请选择作者类型', trigger: 'change' }
            ],
            authors: [
                { required: true, message: '请填写全部作者姓名', trigger: 'change' }
            ],
            cor_author: [
                { required: true, message: '请填写通讯作者', trigger: 'change' }
            ],
            jc_name: [
                { required: true, message: '请填写发表期刊或会议', trigger: 'change' }
            ],
            issn_cn: [
                { required: true, message: '请填写ISSN/CN', trigger: 'change' }
            ],
            impact_factor: [
                { required: true, message: '请填写影响因子', trigger: 'change' }
            ],
            is_award: [
                { required: true, message: '请选择是否有最佳论文奖', trigger: 'change' }
            ],
            pub_status: [
                { required: true, message: '请选择发表状态', trigger: 'change' }
            ],
            sub_date: [
                { required: true, message: '请选择投稿日期', trigger: 'change' }
            ],
            acc_date: [
                { required: true, message: '请选择接收日期', trigger: 'change' }
            ],
            pub_date: [
                { required: true, message: '请选择发表日期', trigger: 'change' }
            ],
            page: [
                { required: false, message: '请填写卷（期）/页码范围', trigger: 'change' }
            ],
            doi_pmid: [
                { required: true, message: '请填写DOI/PMID', trigger: 'change' }
            ],
            paper_type: [
                { required: true, message: '请选择论文类型', trigger: 'change' }
            ],
            ccf_level: [
                { required: true, message: '请选择CCF推荐情况', trigger: 'change' }
            ],
            sci_level: [
                { required: true, message: '请选择中科院分区', trigger: 'change' }
            ],
            include: [
                { required: true, message: '请选择期刊收录情况', trigger: 'change' }
            ],
            publisher: [
                { required: true, message: '请填写出版单位', trigger: 'change' }
            ],
            language: [
                { required: true, message: '请填写论文语言', trigger: 'change' }
            ],
            is_internation: [
                { required: true, message: '请选择是否国际合作论文', trigger: 'change' }
            ],
            is_institution: [
                { required: true, message: '请选择是否本单位合作论文', trigger: 'change' }
            ],
            remark: [
                { required: false, message: '请填写备注', trigger: 'change' }
            ],
            file: [
                { required: true, message: '请上传文件', trigger: 'change' }
            ]
        }
    },
    {
        // 专利
        items: [
            { fieldName: "name", controlType: "input", label: "专利名称", message: '请填写专利名称' },
            { fieldName: "apply_number", controlType: "input", label: "专利申请号", message: '请填写专利申请号' },
            { fieldName: "certificate_number", controlType: "input", label: "专利证书编号", message: '请填写专利证书编号' },
            { fieldName: "inventors", controlType: "input", label: "全部发明人", message: '请按申请人顺序完整填写' },
            { fieldName: "type", controlType: "select", label: "专利类型", message: '请选择专利类型' },
            { fieldName: "is_accept", controlType: "select", label: "是否已受理", message: '请选择是否已受理' },
            { fieldName: "acc_date", controlType: "datepicker", label: "受理时间", message: '请选择受理时间' },
            { fieldName: "is_public", controlType: "select", label: "是否已公开", message: '请选择是否已公开' },
            { fieldName: "pub_date", controlType: "datepicker", label: "公开时间", message: '请选择公开时间' },
            { fieldName: "is_auth", controlType: "select", label: "是否已授权", message: '请选择是否已授权' },
            { fieldName: "auth_date", controlType: "datepicker", label: "授权时间", message: '请选择授权时间' },
            { fieldName: "is_transfer", controlType: "select", label: "是否已转让" },
            { fieldName: "trans_date", controlType: "datepicker", label: "转让时间", message: '请选择转让时间' },
            { fieldName: "trans_income", controlType: "input", label: "转让收入", message: '已转让请填' },
            { fieldName: "remark", controlType: "input", label: "备注", message: '需要特别说明的情况，如有请填写' }
        ],
        selectOptions: {
            type: [
                { value: 'option1', label: '发明专利' },
                { value: 'option2', label: '实用新型' },
                { value: 'option3', label: '外观设计' },
                { value: 'option4', label: 'PCT国际申请' },
                { value: 'option5', label: '其他' }
            ],
            is_accept: [
               { value: 'yes', label: '是' },
            ],
            is_public: [
                { value: 'yes', label: '是' },
            ],
            is_auth: [
                { value: 'yes', label: '是' },
            ],
            is_transfer: [
                { value: 'yes', label: '是' },
            ]
        },
        rules: {
            name: [
                { required: true, message: '请填写专利名称', trigger: 'change' }
            ],
            apply_number: [
                { required: true, message: '请填写专利申请号', trigger: 'change' }
            ],
            certificate_number: [
                { required: true, message: '请填写专利证书编号', trigger: 'change' }
            ],
            inventors: [
                { required: true, message: '请填写全部发明人', trigger: 'change' }
            ],
            type: [
                { required: true, message: '请选择专利类型', trigger: 'change' }
            ],
            is_accept: [
                { required: true, message: '请选择是否已受理', trigger: 'change' }
            ],
            acc_date: [
                { required: true, message: '请选择受理时间', trigger: 'change' }
            ],
            is_public: [
                { required: true, message: '请选择是否已公开', trigger: 'change' }
            ],
            pub_date: [
                { required: true, message: '请选择公开时间', trigger: 'change' }
            ],
            is_auth: [
                { required: true, message: '请选择是否已授权', trigger: 'change' }
            ],
            auth_date: [
                { required: true, message: '请选择授权时间', trigger: 'change' }
            ],
            is_transfer: [
                { required: true, message: '请选择是否已转让', trigger: 'change' }
            ],
            trans_date: [
                { required: false, message: '请选择转让时间', trigger: 'change' }
            ],
            trans_income: [
                { required: false, message: '已转让请填', trigger: 'change' }
            ],
            file: [
                { required: true, message: '请上传文件', trigger: 'change' }
            ]
        }
    },
    {
        // 软件著作权
        items: [
            { fieldName: "name", controlType: "input", label: "软件著作权名称", message: '已受理的按受理通知书，未受理的按合同' },
            { fieldName: "inv_type", controlType: "select", label: "第几发明人" },
            { fieldName: "inventors", controlType: "input", label: "全部完成人", message: '请按发明人顺序完整填写' },
            { fieldName: "app_status", controlType: "select", label: "申请状态" },
            { fieldName: "status_date", controlType: "datepicker", label: "该状态时间" },
            { fieldName: "remark", controlType: "input", label: "备注", message: '需要特别说明的情况，如有' }
        ],
        selectOptions: {
            inv_type: [
                { value: 'communication', label: '通讯' },
                { value: 'first_author', label: '一作' },
                { value: 'communication_first_author', label: '通讯+一作' },
                { value: 'communication_shared_first_author', label: '通讯+共同一作' },
                { value: 'shared_first_author', label: '共同一作' },
                { value: 'second_author', label: '二作' },
                { value: 'other', label: '其他' }
            ],
            app_status: [
                { value: 'applying', label: '申请中' },
                { value: 'received_certificate', label: '收到证书' }
            ]
        },
        rules: {
            name: [
                { required: true, message: '请填写软件著作权名称', trigger: 'change' }
            ],
            inv_type: [
                { required: true, message: '请选择第几发明人', trigger: 'change' }
            ],
            inventors: [
                { required: true, message: '请填写全部完成人', trigger: 'change' }
            ],
            app_status: [
                { required: true, message: '请选择申请状态', trigger: 'change' }
            ],
            status_date: [
                { required: true, message: '请选择该状态时间', trigger: 'change' }
            ],
            remark: [
                { required: false, message: '请填写备注', trigger: 'change' }
            ],
            file: [
                { required: true, message: '请上传文件', trigger: 'change' }
            ]
        }
    },
    {
        // 专著
        items: [
            { fieldName: "title", controlType: "input", label: "专著名称" },
            { fieldName: "author_type", controlType: "select", label: "第几作者" },
            { fieldName: "authors", controlType: "input", label: "该著作全部作者", message: '请按作者顺序依次列出全部作者姓名' },
            { fieldName: "publisher", controlType: "input", label: "出版单位" },
            { fieldName: "category", controlType: "select", label: "著作类别" },
            { fieldName: "isbn", controlType: "input", label: "ISBN" },
            { fieldName: "pub_date", controlType: "datepicker", label: "出版时间" },
            { fieldName: "remark", controlType: "input", label: "备注", message: '需要特别说明的情况，如有' }
        ],
        selectOptions: {
            author_type: [
                { value: 'communication', label: '通讯' },
                { value: 'first_author', label: '一作' },
                { value: 'communication_first_author', label: '通讯+一作' },
                { value: 'communication_shared_first_author', label: '通讯+共同一作' },
                { value: 'shared_first_author', label: '共同一作' },
                { value: 'second_author', label: '二作' },
                { value: 'other', label: '其他' }
            ],
            category: [
                { value: 'research_work', label: '研究型著作' },
                { value: 'textbook', label: '教材' },
                { value: 'translation', label: '译著' },
                { value: 'editing', label: '编著' }
            ]
        },
        rules: {
            title: [
                { required: true, message: '请填写专著名称', trigger: 'change' }
            ],
            author_type: [
                { required: true, message: '请选择第几作者', trigger: 'change' }
            ],
            authors: [
                { required: true, message: '请填写该著作全部作者', trigger: 'change' }
            ],
            publisher: [
                { required: true, message: '请填写出版单位', trigger: 'change' }
            ],
            category: [
                { required: true, message: '请选择著作类别', trigger: 'change' }
            ],
            isbn: [
                { required: true, message: '请填写ISBN', trigger: 'change' }
            ],
            pub_date: [
                { required: true, message: '请选择出版时间', trigger: 'change' }
            ],
            remark: [
                { required: false, message: '请填写备注', trigger: 'change' }
            ],
            file: [
                { required: true, message: '请上传文件', trigger: 'change' }
            ]
        }
    },
    {
        // 赴校外交流
        items: [
            { fieldName: "name", controlType: "input", label: "项目名称" },
            { fieldName: "type", controlType: "select", label: "事由类型" },
            { fieldName: "funding", controlType: "input", label: "经费来源" },
            { fieldName: "dest_country", controlType: "input", label: "前往国家/地区" },
            { fieldName: "dest_city", controlType: "input", label: "前往城市" },
            { fieldName: "dest_institution", controlType: "input", label: "前往院校/机构名称或竞赛/学术会议名称" },
            { fieldName: "duration", controlType: "select", label: "项目期限" },
            { fieldName: "begin", controlType: "datepicker", label: "出发时间" },
            { fieldName: "end", controlType: "datepicker", label: "返校时间" },
            { fieldName: "status", controlType: "select", label: "目前状态" },
            { fieldName: "is_apply", controlType: "select", label: "是否已完成申报/报备" },
            { fieldName: "is_pre_edu", controlType: "select", label: "是否已参加行前教育" },
            { fieldName: "is_signed", controlType: "select", label: "是否已签订《学生赴校外交流学习承诺书》《学生声明》《亲属声明》" },
            { fieldName: "is_talk", controlType: "select", label: "是否已开展谈心谈话" },
            { fieldName: "remark", controlType: "input", label: "备注", message: '若“事由类型”选其他，请备注' }
        ],
        selectOptions: {
            type: [
                { value: 'exchange', label: '交换项目' },
                { value: 'academic_conference', label: '学术会议' },
                { value: 'short_term_academic_exchange', label: '短期学术交流' },
                { value: 'short_term_course', label: '短期课程' },
                { value: 'chinese_teacher_volunteer', label: '汉语教师志愿者' },
                { value: 'internship', label: '实习' },
                { value: 'competition', label: '竞赛' },
                { value: 'other', label: '其他' }
            ],
            duration: [
                { value: 'long_term', label: '长期（180天以上）' },
                { value: 'short_term', label: '短期（180天以下）' }
            ],
            status: [
                { value: 'returned', label: '已返校' },
                { value: 'abroad', label: '在外' },
                { value: 'upcoming', label: '即将赴外' }
            ],
            is_apply: [
                { value: 'yes', label: '是' },
                { value: 'no', label: '否' }
            ],
            is_pre_edu: [
                { value: 'yes', label: '是' },
                { value: 'no', label: '否' }
            ],
            is_signed: [
                { value: 'yes', label: '是' },
                { value: 'no', label: '否' }
            ],
            is_talk: [
                { value: 'yes', label: '是' },
                { value: 'no', label: '否' }
            ]
        },
        rules: {
            name: [
                { required: true, message: '请填写项目名称', trigger: 'change' }
            ],
            type: [
                { required: true, message: '请选择事由类型', trigger: 'change' }
            ],
            funding: [
                { required: true, message: '请填写经费来源', trigger: 'change' }
            ],
            dest_country: [
                { required: true, message: '请填写前往国家/地区', trigger: 'change' }
            ],
            dest_city: [
                { required: true, message: '请填写前往城市', trigger: 'change' }
            ],
            dest_institution: [
                { required: true, message: '请填写前往院校/机构名称或竞赛/学术会议名称', trigger: 'change' }
            ],
            duration: [
                { required: true, message: '请选择项目期限', trigger: 'change' }
            ],
            begin: [
                { required: true, message: '请选择出发时间', trigger: 'change' }
            ],
            end: [
                { required: true, message: '请选择返校时间', trigger: 'change' }
            ],
            status: [
                { required: true, message: '请选择目前状态', trigger: 'change' }
            ],
            is_apply: [
                { required: true, message: '请选择是否已完成申报/报备', trigger: 'change' }
            ],
            is_pre_edu: [
                { required: true, message: '请选择是否已参加行前教育', trigger: 'change' }
            ],
            is_signed: [
                { required: true, message: '请选择是否已签订相关声明', trigger: 'change' }
            ],
            is_talk: [
                { required: true, message: '请选择是否已开展谈心谈话', trigger: 'change' }
            ],
            remark: [
                { required: false, message: '请填写备注', trigger: 'change' }
            ],
            file: [
                { required: true, message: '请上传文件', trigger: 'change' }
            ]
        }
    },
    {
        // 志愿活动
        items: [
            { fieldName: "organization", controlType: "input", label: "志愿组织团体", message: '全称' },
            { fieldName: "name", controlType: "input", label: "志愿活动名称", message: '全称' },
            { fieldName: "type", controlType: "select", label: "志愿活动类型" },
            { fieldName: "begin", controlType: "datepicker", label: "参与志愿活动开始时间" },
            { fieldName: "end", controlType: "datepicker", label: "参与志愿活动结束时间" },
            { fieldName: "time", controlType: "input", label: "志愿服务时长", message: 'X小时' }
        ],
        selectOptions: {
            type: [
                { value: 'educational_support_teaching', label: '助学支教' },
                { value: 'cultural_artistic_service', label: '文化文艺服务' },
                { value: 'scientific_popularization', label: '科学普及' },
                { value: 'theoretical_policy_lecture', label: '理论政策宣讲' },
                { value: 'elderly_service', label: '敬老服务' },
                { value: 'disability_service', label: '助残服务' },
                { value: 'civilized_traffic', label: '文明交通' },
                { value: 'health_environmental_protection', label: '卫生环保' },
                { value: 'poverty_alleviation_assistance', label: '扶贫帮困' },
                { value: 'sports_activities', label: '体育活动' },
                { value: 'welcoming_activities', label: '迎新活动' },
                { value: 'three_go_to_the_countryside', label: '三下乡' },
                { value: 'conference_services', label: '会务工作' },
                { value: 'medical_services', label: '医疗服务' },
                { value: 'other', label: '其他' }
            ]
        },
        rules: {
            organization: [
                { required: true, message: '请填写志愿组织团体', trigger: 'change' }
            ],
            name: [
                { required: true, message: '请填写志愿活动名称', trigger: 'change' }
            ],
            type: [
                { required: true, message: '请选择志愿活动类型', trigger: 'change' }
            ],
            begin: [
                { required: true, message: '请选择参与志愿活动开始时间', trigger: 'change' }
            ],
            end: [
                { required: true, message: '请选择参与志愿活动结束时间', trigger: 'change' }
            ],
            time: [
                { required: true, message: '请填写志愿服务时长', trigger: 'change' }
            ],
            file: [
                { required: true, message: '请上传文件', trigger: 'change' }
            ]
        }
    },
    {
        // 其他
        items: [
            { fieldName: "discribe", controlType: "input", label: "具体描述" },
            { fieldName: "attend_time", controlType: "datepicker", label: "参与时间" },
        ],
        selectOptions: {
        },
        rules: {
            discribe: [
                { required: true, message: '请填写具体描述', trigger: 'change' }
            ],
            attend_time: [
                { required: true, message: '请选择开始时间', trigger: 'change' }
            ],
            file: [
                { required: true, message: '请上传文件', trigger: 'change' }
            ]
        }
    },
    {
        // 社会工作 思想品德
        items: [
            { fieldName: "commendation", controlType: "input", label: "获得表彰" },
            { fieldName: "attend_time", controlType: "datepicker", label: "参与时间" },
        ],
        selectOptions: {
        },
        rules: {
            commendation: [
                { required: true, message: '请填写获得表彰', trigger: 'change' }
            ],
            attend_time: [
                { required: true, message: '请选择开始时间', trigger: 'change' }
            ],
            file: [
                { required: true, message: '请上传文件', trigger: 'change' }
            ]
        }
    },

    
]

export default InfoSubmissionsData