<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- 引入样式 -->
<html>
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<%--<link rel="stylesheet" href="/js/index.css">--%>

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
        </div>
</div>
</body>
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
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
                        value: '' ,
                        input: '',
                        result: '',
                        formInline: {
                                type: '1' ,
                                account: '',
                                pwd: ''
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
                                console.log(this.result);
                                this.value = this.result.password;
                                console.log(this.value);
                        })
                        .catch(function (error) { // 请求失败处理
                                console.log(error);
                        });
                }
        }
})
</script>
</html>
