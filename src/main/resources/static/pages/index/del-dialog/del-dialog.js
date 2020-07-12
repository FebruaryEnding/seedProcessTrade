(function (owner) {

    owner.show = function (delId) {
        vm.dialogVisible = true

        vm.delId = delId

        var baseInfo = _loacalStorage.get('baseInfo')
        vm.model.dataInfo.operateNumber = _pub.GetObjProperty(baseInfo, 'operateNumber')
    }

    var template =
        `
            <div class="del-dialog">
                <el-dialog title="删除" :visible="dialogVisible" width="500px" @close="onDialogCancel">
                    <el-form :model="model.dataInfo" :rules="model.rules" ref="del-dialog" label-width="100px">
                        <el-form-item label="删除密钥" prop="operateNumber">
                            <el-input style="width: 300px;" show-password v-model="model.dataInfo.operateNumber"
                                placeholder="请输入删除密钥">
                            </el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                        <el-button @click="onDialogCancel">取 消</el-button>
                        <el-button type="primary" :loading="dialogLoading" @click="onDel">确 定</el-button>
                    </span>
                </el-dialog>
            </div>
        `

    var vm = new Vue({
        el: '.del-dialog',
        template: template,
        data() {
            return {
                delId: '',
                dialogVisible: false,
                dialogLoading: false,
                model: {
                    rules: {
                        operateNumber: [
                            { required: true, message: '请输入删除密钥', trigger: 'change' }
                        ],
                    },
                    dataInfo: {
                        operateNumber: '',
                    }
                },
            }
        },
        methods: {
            onDialogCancel() {
                this.$refs['del-dialog'].resetFields()

                this.dialogVisible = false
            },
            onDel() {
                this.$refs['del-dialog'].validate(function (valid) {
                    if (valid) {

                        this.dialogLoading = true

                        _ajax.DELETE(`/seed/${this.delId}/${this.model.dataInfo.operateNumber}`, {}, {
                            complete: function () {
                                this.dialogLoading = false
                            }.bind(this),

                            success: function (res) {
                                _pub.Notify(this, { title: '删除', message: res })

                                this.onDialogCancel()

                                _list.render()
                            }.bind(this)
                        })
                    }
                }.bind(this))
            },
        }
    })


})(window._delDialog = {})