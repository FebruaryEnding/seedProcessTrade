(function (owner) {

    owner.show = function () {
        vm.visible = true
    }

    var template =
        `
            <div class="login-box" v-if="visible">
                <p class="title">登录</p>

                <div class="login-con">
                    <el-input placeholder="请输入用户名" prefix-icon="el-icon-user-solid" v-model="loginInfo.userName">
                    </el-input>

                    <el-input style="margin-top: 20px;" placeholder="请输入密码" show-password prefix-icon="el-icon-lock"
                        v-model="loginInfo.password">
                    </el-input>

                    <el-button style="width: 100%;margin-top: 20px;" type="primary" @click="onLogin" :loading='loading'>
                        登录</el-button>

                    <el-button style="margin-top: 10px;" type="text" @click="gotoReg">去注册</el-button>
                </div>
            </div>
        `

    var vm = new Vue({
        el: '.login-box',
        template: template,
        data() {
            return {
                visible: true,
                loading: false,
                loginInfo: {
                    userName: '',
                    password: '',
                },
            }
        },
        methods: {
            onLogin() {
                this.loading = true

                _ajax.POST(`/user/login`, this.loginInfo, {
                    complete: function () {
                        this.loading = false
                    }.bind(this),

                    success: function (res) {
                        var data = _pub.GetObjProperty(res, 'data')

                        _loacalStorage.set('userInfo', data)

                        this.resetData()

                    }.bind(this)
                })
            },
            resetData() {
                this.loginInfo = {
                    userName: '',
                    password: '',
                }
            },
            gotoReg() {
                this.visible = false

                _reg.show()
            },
            gotoHome() {
                window.history.back()
            },
        },
    })
})(window._login = {})