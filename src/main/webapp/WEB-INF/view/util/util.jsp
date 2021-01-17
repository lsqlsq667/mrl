<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- 引入样式 -->
<html>
<%--<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">--%>
<link rel="stylesheet" href="/js/index.css">

<body>
<div style="text-align:center;margin-top: 150px;">
        <div id="app">
                <el-form :inline="true" :model="formInline" class="demo-form-inline">
                        <el-form-item label="账号">
                                <el-input v-model="formInline.account" placeholder="账号"></el-input>
                        </el-form-item>
                        <el-form-item label="密码">
                                <el-input v-model="formInline.pwd" placeholder="密码"></el-input>
                        </el-form-item>
                        <el-form-item>
                                <el-radio v-model="formInline.type" label="1">解密</el-radio>
                                <el-radio v-model="formInline.type" label="2">加密</el-radio>
                        </el-form-item>
                        <el-form-item>
                                <el-button type="primary" @click="onSubmit">查询</el-button>
                        </el-form-item>

                </el-form>
                <div style="margin: 20px 0;"></div>
                <el-input style="width: 520px"
                          type="textarea"
                          :autosize="{ minRows: 2, maxRows: 4}"
                          v-model="value">
                </el-input>
                <div style="width: 800px;margin: auto;">
                        <el-table
                                :key="tableKey"
                                :data="tableData"
                                border
                                style="width: 800px;margin-top: 100px;margin-bottom: 200px;">
                                <el-table-column
                                        prop="account"
                                        label="账号"
                                        width="150">
                                </el-table-column>
                                <el-table-column
                                        prop="pwd"
                                        label="密码"
                                        width="150">
                                </el-table-column>
                                <el-table-column
                                        prop="type"
                                        label="类型"
                                        width="150">
                                </el-table-column>
                                <el-table-column
                                        prop="content"
                                        label="加解密内容"
                                        width="150">
                                </el-table-column>
                                <el-table-column
                                        prop="time"
                                        label="加解密时间"
                                        width="180">
                                </el-table-column>

                        </el-table>
                </div>
        </div>
</div>
</body>
<!-- import Vue before Element -->
<script src="/js/vue.js"></script>
<%--<script src="https://unpkg.com/axios/dist/axios.min.js"></script>--%>
<script src="/js/min/axios.min.js"></script>
<%--<script src="/js/vue.js"></script>--%>
<!-- import JavaScript -->
<%--<script src="https://unpkg.com/element-ui/lib/index.js"></script>--%>
<script src="/js/index.js"></script>
<script>
new Vue({
        el: '#app',
        data: function() {
                return {
                        visible: false ,
                        tableKey: 0 ,
                        value: '' ,
                        input: '',
                        result: '',
                        formInline: {
                                type: '1' ,
                                account: '',
                                pwd: ''
                        },
                        tableData: [
                        ],
                        tempResult:{
                                account:'123',
                                pwd: '123',
                                type: '123',
                                content: '123',
                                time: '123'
                        }
                }
        },
        methods: {
                onSubmit() {
                        if (this.formInline.account === '' || this.formInline.pwd === '') {
                                this.$message({
                                        message: '请求数据有空值',
                                        showClose: true,
                                        type: 'error'
                                });
                                return;
                        }
                        axios
                        .get('/encryption/decryption',{'params':this.formInline})
                        .then(response => {
                                this.result = response.data;
                                this.value = this.result.password;
                                var tempResults = {
                                        account:'',
                                        pwd: '',
                                        type: '',
                                        content: '',
                                        time: ''
                                }
                                tempResults.account = this.result.account
                                tempResults.pwd = this.formInline.pwd
                                if (this.formInline.type === '1') {
                                        tempResults.type = '解密'
                                }else {
                                        tempResults.type = '加密'
                                }
                                tempResults.content = this.result.password
                                tempResults.time = this.getTimeStr("yyyy-MM-dd hh:mm:ss")
                                let len = this.tableData.length;
                                //在数组最前面插入数据
                                this.tableData.unshift(tempResults);
                                this.tableKey += 1
                        })
                        .catch(function (error) { // 请求失败处理
                                console.log(error);
                        });
                },
                getTimeStr(fmt){
                        var myDate = new Date();
                        var o = {
                                "M+": myDate.getMonth() + 1, // 月份
                                "d+": myDate.getDate(), // 日
                                "h+": myDate.getHours(), // 小时
                                "m+": myDate.getMinutes(), // 分
                                "s+": myDate.getSeconds(), // 秒
                                "q+": Math.floor((myDate.getMonth() + 3) / 3), // 季度
                                "S": myDate.getMilliseconds() // 毫秒
                        };
                        if (/(y+)/.test(fmt))
                                fmt = fmt.replace(RegExp.$1, (myDate.getFullYear() + "").substr(4 - RegExp.$1.length));
                        for (var k in o)
                                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                        return fmt;
                }
        }
})
</script>
</html>
