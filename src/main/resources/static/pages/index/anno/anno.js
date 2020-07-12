(function (owner) {

    var template =
        `
            <div class="anno-box">
                <el-alert v-if="anno" :closable="false" :title="anno" type="warning" show-icon>
                </el-alert>
            </div>
        `

    var vm = new Vue({
        el: '.anno-box',
        template: template,
        data() {
            return {
                anno: '',
            }
        },
        mounted() {
            // 获取公告
            this.getAnno()
        },
        methods: {
            getAnno() {
                _ajax.GET(`/seed/anno`, {}, {
                    success: function (res) {
                        this.anno = res
                    }.bind(this)
                })
            },
        },
    })
})(window._anno = {})