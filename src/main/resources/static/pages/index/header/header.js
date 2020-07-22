(function (owner) {

    var template =
        `
        <div class="header">
            <header>
                <div class="userInfo">
                    <el-popover placement="bottom" width="200" trigger="hover" v-if="_pub.GetObjProperty(userInfo, 'id')">
                        <div>
                            <el-button style="width: 100%;" type="danger" plain @click="_pub.GotoLogin">退出登录</el-button>
                        </div>
                        <el-button type="text" class="userName" slot="reference">{{_pub.GetObjProperty(userInfo, 'roleName')}}【{{_pub.GetObjProperty(userInfo, 'serverName')}}】</el-button>
                    </el-popover>
                    <el-button class="login" type="text" v-else @click="_pub.GotoLogin">您未登录，请先登录</el-button>
                </div>
            </header>
        </div>
        `

    var vm = new Vue({
        el: '.header',
        template: template,
        data() {
            return {
                userInfo: _loacalStorage.get('userInfo'),
            }
        },
        methods: {},
    })
})(window._header = {})