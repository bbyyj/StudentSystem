const StudentInfoData = [
    // 0 管理员/班主任-查看信息用
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
                { value: '港澳台生', label: '港澳台生' },
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
    // 1 管理员/班主任-去掉职位，添加用，添加默认非班长
    {
        items: [
            { fieldName: 'admissionYear', label: '入学年份', width: '70px', controlType: 'input' },
            { fieldName: 'classId', label: '班级', width: '70px', controlType: 'select' },
            { fieldName: 'name', label: '姓名', width: '100px', controlType: 'input' },
            // { fieldName: 'type', label: '学生类别', width: '90px', controlType: 'select' },
            { fieldName: 'netId', label: 'netId', width: '60px',  controlType: 'input' },
            // { fieldName: 'nation', label: '民族', width: '60px', controlType: 'input' },
            { fieldName: 'sid', label: '学号', width: '100px', controlType: 'input' },
            // { fieldName: 'pid', label: '身份证号', width: '200px', controlType: 'input' },
            // { fieldName: 'birth', label: '出生年月', width: '100px', controlType: 'datepicker' },
            { fieldName: 'undergraduate', label: '培养层次', width: '100px', controlType: 'select' },
            // { fieldName: 'politics', label: '政治面貌', width: '90px', controlType: 'select' },
            // { fieldName: 'nativePlace', label: '籍贯', width: '150px', controlType: 'input' },
            // { fieldName: 'dormitory', label: '宿舍', width: '150px', controlType: 'input' },
        ],
        selectOptions: {
            classId: [
                { value: '0', label: '研究生' },
                { value: '1', label: '1班' },
                { value: '2', label: '2班' },
                { value: '3', label: '3班' },
                { value: '4', label: '4班' },
            ],
            // type: [
            //     { value: '港澳台生', label: '港澳台生' },
            //     { value: '境内生', label: '境内生' },
            //     { value: '国际生', label: '国际生' },
            // ],
            undergraduate: [
                { value: 'true', label: '本科生' },
                { value: 'false', label: '研究生' },
            ],
            // politics: [
            //     { value: '共青团员', label: '共青团员' },
            //     { value: '群众', label: '群众' },
            //     { value: '无', label: '无' },
            // ]
        
        },
    },
    // 2 top管理员-搜索
    {
        items:[
            { fieldName: 'admissionYear', label: '入学年份' },
            { fieldName: 'classId', label: '班级' },
            { fieldName: 'name', label: '姓名' },
            { fieldName: 'studentRole', label: '学生职位' },
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
    // 3 班主任-搜索，去掉班级相关信息
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
    // 4 班长-搜索，去掉隐私信息
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
    // 5 班长-信息显示
    {
        items:[
            { fieldName: 'admissionYear', label: '入学年份', width: '70px' },
            { fieldName: 'classId', label: '班级', width: '70px' },
            { fieldName: 'name', label: '姓名', width: '100px' },
            { fieldName: 'type', label: '学生类别', width: '90px' },
            { fieldName: 'nation', label: '民族', width: '60px' },
            { fieldName: 'sid', label: '学号', width: '100px' },
            { fieldName: 'birth', label: '出生年月', width: '100px' },
            { fieldName: 'politics', label: '政治面貌', width: '90px' },
            { fieldName: 'nativePlace', label: '籍贯', width: '150px' },
            { fieldName: 'dormitory', label: '宿舍', width: '150px' },
            { fieldName: 'phone', label: '个人联系电话', width: '150px' },
            { fieldName: 'email', label: '邮箱', width: '150px' },
            { fieldName: 'wechat', label: '微信', width: '150px' }
        ], 
    },
    // 6 管理员/班主任-编辑信息用，去掉了联系方式
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
                { value: '港澳台生', label: '港澳台生' },
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
]

export default StudentInfoData