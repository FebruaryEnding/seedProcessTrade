(function (owner) {

    owner.show = function () {
        vm.visible = true
    }

    var template =
        `
            <div class="reg-box" v-if="visible">
                <p class="title">注册</p>

                <div class="reg-con">
                <el-form ref="reg" :model="model.dataInfo" :rules="model.rules" label-width="90px">

                <el-form-item label="游戏账号" prop="accountNumber">
                <el-input style="width: 310px;" placeholder="请输入游戏账号" v-model="model.dataInfo.accountNumber" @change="getRoleList"></el-input-number>
                </el-form-item>

                <el-form-item label="服务器" prop="serverName">
                <el-select style="width: 310px;" placeholder="请选择服务器" v-model="model.dataInfo.serverName" @change="getRoleList">
                            <el-option v-for="item in serverType" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                </el-form-item>

                <el-form-item label="角色信息" prop="roleInfo">
                <el-select style="width: 310px;" placeholder="输入游戏账号和服务器查询角色信息" v-model="model.dataInfo.roleInfo">
                            <el-option v-for="item in roleList" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                </el-form-item>

                <el-form-item label="用户名" prop="userName">
                <el-input style="width: 310px;" placeholder="请输入登录用户名" v-model="model.dataInfo.userName"></el-input-number>
                </el-form-item>

                <el-form-item label="密码" prop="password">
                <el-input style="width: 310px;" placeholder="请输入登录密码" v-model="model.dataInfo.password" show-password></el-input-number>
                </el-form-item>

                </el-form>

                <el-button style="width: 100%;margin-top: 10px;" type="primary" @click="onReg" :loading='loading'>
                        注册</el-button>

                    <el-button style="margin-top: 10px;" type="text" @click="gotoLogin">去登录</el-button>

                </div>
            </div>
        `

    var vm = new Vue({
        el: '.reg-box',
        template: template,
        data() {
            return {
                visible: false,
                loading: false,
                serverType: _dataDic.get('serverType'),
                roleList: [],
                model: {
                    rules: {
                        accountNumber: [
                            { required: true, message: ' ', trigger: 'change' }
                        ],
                        serverName: [
                            { required: true, message: ' ', trigger: 'change' }
                        ],
                        roleInfo: [
                            { required: true, message: ' ', trigger: 'change' }
                        ],
                        userName: [
                            { required: true, message: ' ', trigger: 'change' }
                        ],
                        password: [
                            { required: true, message: ' ', trigger: 'change' }
                        ],
                    },
                    dataInfo: {
                        accountNumber: "",
                        serverName: "",
                        roleInfo: '',
                        roleName: "",
                        roleLevel: 0,
                        userName: "",
                        password: "",
                    },
                },
            }
        },
        methods: {
            getRoleList() {
                var dataInfo = this.model.dataInfo

                this.roleList = []
                dataInfo.roleInfo = ''

                if (dataInfo.accountNumber && dataInfo.serverName) {
                    _ajax.GET(`/user`, {
                        accountNumber: dataInfo.accountNumber,
                        serverName: dataInfo.serverName,
                    }, {
                        success: function (res) {
                            var data = _pub.IsArray(_pub.GetObjProperty(res, 'data'))

                            this.roleList = data.map(function (item) {
                                var text = `${_pub.GetObjProperty(item, 'name')} - ${_pub.GetObjProperty(item, 'level')}`

                                return {
                                    value: text,
                                    label: text,
                                }
                            })
                        }.bind(this)
                    })
                }
            },
            onReg() {
                this.$refs['reg'].validate(function (valid) {
                    if (valid) {
                        var dataInfo = this.model.dataInfo

                        var roleInfo = dataInfo.roleInfo
                        var roleInfoArr = roleInfo.split(' - ')

                        dataInfo.roleName = roleInfoArr[0]
                        dataInfo.roleLevel = roleInfoArr[1]

                        this.loading = true

                        _ajax.POST(`/user/register`, dataInfo, {
                            complete: function () {
                                this.loading = false
                            }.bind(this),

                            success: function (res) {
                                _pub.Notify(this, { title: '注册', message: _pub.GetObjProperty(res, 'msg') })

                                this.gotoLogin()
                            }.bind(this)
                        })
                    }
                }.bind(this))
            },
            gotoLogin() {
                this.$refs['reg'].resetFields()

                this.visible = false

                _login.show()
            },
        },
    })
})(window._reg = {})