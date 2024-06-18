const StudentInfoData = [
    // 0 编辑和查看信息用
    {
        items: [
            { fieldName: 'admissionYear', label: '入学年份', width: '70px', controlType: 'input' },
            { fieldName: 'classId', label: '班级', width: '70px', controlType: 'select' },
            { fieldName: 'name', label: '姓名', width: '100px', controlType: 'input' },
            { fieldName: 'studentRole', label: '学生职位', width: '100px', controlType: 'select' },
            { fieldName: 'type', label: '学生类别', width: '90px', controlType: 'select' },
            { fieldName: 'netId', label: 'netId', width: '60px',  controlType: 'input' },
            { fieldName: 'nation', label: '民族', width: '60px', controlType: 'input' },
            { fieldName: 'sid', label: '学号', width: '100px', controlType: 'input' },
            { fieldName: 'pid', label: '身份证号', width: '200px', controlType: 'input' },
            { fieldName: 'birth', label: '出生年月', width: '100px', controlType: 'datepicker' },
            { fieldName: 'undergraduate', label: '培养层次', width: '100px', controlType: 'select' },
            { fieldName: 'politics', label: '政治面貌', width: '90px', controlType: 'select' },
            { fieldName: 'nativePlace', label: '籍贯', width: '150px', controlType: 'input' },
            { fieldName: 'dormitory', label: '宿舍', width: '150px', controlType: 'input' },
            { fieldName: 'phone', label: '个人联系电话', width: '150px', controlType: 'input' },
            { fieldName: 'address', label: '详细家庭住址', width: '250px', controlType: 'input' },
            { fieldName: 'urgentPhone', label: '紧急联系人电话', width: '150px', controlType: 'input' },
            { fieldName: 'urgentName', label: '紧急联系人', width: '110px', controlType: 'input' },
            { fieldName: 'email', label: '邮箱', width: '150px', controlType: 'input' },
            { fieldName: 'wechat', label: '微信', width: '150px', controlType: 'input' }
        ],
        selectOptions: {
            classId: [
                { value: '0', label: '研究生' },
                { value: '1', label: '1班' },
                { value: '2', label: '2班' },
                { value: '3', label: '3班' },
                { value: '4', label: '4班' },
            ],
            studentRole: [
                { value: 'NOT_MONITOR', label: '学生' },
                { value: 'MONITOR', label: '班长' },
            ],
            type: [
                { value: '港台生', label: '澳台生' },
                { value: '境内生', label: '境内生' },
                { value: '国际生', label: '国际生' },
            ],
            undergraduate: [
                { value: 'true', label: '本科生' },
                { value: 'false', label: '研究生' },
            ],
            politics: [
                { value: '共青团员', label: '共青团员' },
                { value: '群众', label: '群众' },
                { value: '无', label: '无' },
            ]
        
        },
    },
    // 1 去掉职位，添加用，添加默认非班长
    {
        items: [
            { fieldName: 'admissionYear', label: '入学年份', width: '70px', controlType: 'input' },
            { fieldName: 'classId', label: '班级', width: '70px', controlType: 'select' },
            { fieldName: 'name', label: '姓名', width: '100px', controlType: 'input' },
            { fieldName: 'type', label: '学生类别', width: '90px', controlType: 'select' },
            { fieldName: 'netId', label: 'netId', width: '60px',  controlType: 'input' },
            { fieldName: 'nation', label: '民族', width: '60px', controlType: 'input' },
            { fieldName: 'sid', label: '学号', width: '100px', controlType: 'input' },
            { fieldName: 'pid', label: '身份证号', width: '200px', controlType: 'input' },
            { fieldName: 'birth', label: '出生年月', width: '100px', controlType: 'datepicker' },
            { fieldName: 'undergraduate', label: '培养层次', width: '100px', controlType: 'select' },
            { fieldName: 'politics', label: '政治面貌', width: '90px', controlType: 'select' },
            { fieldName: 'nativePlace', label: '籍贯', width: '150px', controlType: 'input' },
            { fieldName: 'dormitory', label: '宿舍', width: '150px', controlType: 'input' },
            { fieldName: 'phone', label: '个人联系电话', width: '150px', controlType: 'input' },
            { fieldName: 'address', label: '详细家庭住址', width: '250px', controlType: 'input' },
            { fieldName: 'urgentPhone', label: '紧急联系人电话', width: '150px', controlType: 'input' },
            { fieldName: 'urgentName', label: '紧急联系人', width: '110px', controlType: 'input' },
            { fieldName: 'email', label: '邮箱', width: '150px', controlType: 'input' },
            { fieldName: 'wechat', label: '微信', width: '150px', controlType: 'input' }
        ],
        selectOptions: {
            classId: [
                { value: '0', label: '研究生' },
                { value: '1', label: '1班' },
                { value: '2', label: '2班' },
                { value: '3', label: '3班' },
                { value: '4', label: '4班' },
            ],
            type: [
                { value: '港台生', label: '澳台生' },
                { value: '境内生', label: '境内生' },
                { value: '国际生', label: '国际生' },
            ],
            undergraduate: [
                { value: 'true', label: '本科生' },
                { value: 'false', label: '研究生' },
            ],
            politics: [
                { value: '共青团员', label: '共青团员' },
                { value: '群众', label: '群众' },
                { value: '无', label: '无' },
            ]
        
        },
    },
    // 2 top管理员搜索
    {
        items:[
            { fieldName: 'admissionYear', label: '入学年份' },
            { fieldName: 'classId', label: '班级' },
            { fieldName: 'name', label: '姓名' },
            { fieldName: 'type', label: '学生类别' },
            { fieldName: 'netId', label: 'netId' },
            { fieldName: 'nation', label: '民族' },
            { fieldName: 'sid', label: '学号' },
            { fieldName: 'pid', label: '身份证号' },
            { fieldName: 'birth', label: '出生年月' },
            { fieldName: 'undergraduate', label: '培养层次' },
            { fieldName: 'politics', label: '政治面貌' },
            { fieldName: 'nativePlace', label: '籍贯' },
            { fieldName: 'dormitory', label: '宿舍' },
            { fieldName: 'phone', label: '个人联系电话' },
            { fieldName: 'address', label: '详细家庭住址' },
            { fieldName: 'urgentPhone', label: '紧急联系人电话' },
            { fieldName: 'urgentName', label: '紧急联系人' },
            { fieldName: 'email', label: '邮箱' },
            { fieldName: 'wechat', label: '微信' }
        ],

        
    },
    // 3 班主任搜索，去掉班级相关信息
    {
        items:[
            { fieldName: 'name', label: '姓名' },
            { fieldName: 'type', label: '学生类别' },
            { fieldName: 'netId', label: 'netId' },
            { fieldName: 'nation', label: '民族' },
            { fieldName: 'sid', label: '学号' },
            { fieldName: 'pid', label: '身份证号' },
            { fieldName: 'birth', label: '出生年月' },
            { fieldName: 'undergraduate', label: '培养层次' },
            { fieldName: 'politics', label: '政治面貌' },
            { fieldName: 'nativePlace', label: '籍贯' },
            { fieldName: 'dormitory', label: '宿舍' },
            { fieldName: 'phone', label: '个人联系电话' },
            { fieldName: 'address', label: '详细家庭住址' },
            { fieldName: 'urgentPhone', label: '紧急联系人电话' },
            { fieldName: 'urgentName', label: '紧急联系人' },
            { fieldName: 'email', label: '邮箱' },
            { fieldName: 'wechat', label: '微信' }
        ],
    },
    // 4 班长搜索，去掉隐私信息
    {
        items:[
            { fieldName: 'name', label: '姓名' },
            { fieldName: 'type', label: '学生类别' },
            { fieldName: 'nation', label: '民族' },
            { fieldName: 'sid', label: '学号' },
            { fieldName: 'birth', label: '出生年月' },
            { fieldName: 'politics', label: '政治面貌' },
            { fieldName: 'nativePlace', label: '籍贯' },
            { fieldName: 'dormitory', label: '宿舍' },
            { fieldName: 'phone', label: '个人联系电话' },
            { fieldName: 'email', label: '邮箱' },
            { fieldName: 'wechat', label: '微信' }
        ],

        
    },
]

export default StudentInfoData