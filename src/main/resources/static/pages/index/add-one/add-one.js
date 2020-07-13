(function (owner) {

    owner.show = function () {
        vm.dialogVisible = true

        var baseInfo = _loacalStorage.get('baseInfo')
        vm.model.dataInfo.roleName = _pub.GetObjProperty(baseInfo, 'roleName')
        vm.model.dataInfo.operateNumber = _pub.GetObjProperty(baseInfo, 'operateNumber')

        // 获取种植工艺列表
        vm.getNameList()
    }

    var template =
        `
        <div class="add-one">
            <el-dialog title="添加种植工艺" :visible="dialogVisible" width="500px" @close="onDialogCancel">
                <el-form :model="model.dataInfo" :rules="model.rules" ref="addOne" label-width="100px">

                    <el-form-item label="种植工艺" prop="name">
                        <el-select v-model="model.dataInfo.name" style="width: 300px;" filterable placeholder="搜索种植工艺名称">
                            <el-option style="width: 600px;" v-for="item in nameList" :key="item.value" :label="item.value"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="等级" prop="level">
                        <el-input-number style="width: 300px;" v-model="model.dataInfo.level" :precision="0"></el-input-number>
                    </el-form-item>

                    <el-form-item label="角色名" prop="roleName">
                        <el-input style="width: 300px;" v-model="model.dataInfo.roleName" placeholder="请输入角色名"></el-input>
                    </el-form-item>

                    <el-form-item label="服务器" prop="serverName">
                        <el-select style="width: 300px;" v-model="model.dataInfo.serverName">
                            <el-option v-for="item in serverType" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="买卖" prop="sellOrBuy">
                        <el-select style="width: 300px;" v-model="model.dataInfo.sellOrBuy">
                            <el-option v-for="item in saleType" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="数量" prop="number">
                        <el-input-number style="width: 300px;" v-model="model.dataInfo.number" :precision="0"></el-input-number>
                    </el-form-item>

                    <el-form-item label="单价/单位" prop="price">
                        <el-input placeholder="请输入内容" type="number" v-model="model.dataInfo.price" style="width: 300px;"
                            class="input-with-select">
                            <el-select style="width: 80px;" v-model="model.dataInfo.unit" slot="append">
                                <el-option v-for="item in unitType" :key="item.value" :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>

                    <el-form-item label="删除密钥" prop="operateNumber">
                        <el-input style="width: 300px;" show-password v-model="model.dataInfo.operateNumber" placeholder="请输入删除密钥(删除操作需要)">
                        </el-input>
                    </el-form-item>

                </el-form>

                <span slot="footer" class="dialog-footer">
                    <el-button @click="onDialogCancel">取 消</el-button>
                    <el-button type="primary" :loading="dialogLoading" @click="onDialogOk">确 定</el-button>
                </span>
            </el-dialog>
        </div>
        `

    var vm = new Vue({
        el: '.add-one',
        template: template,
        data() {
            return {
                dialogVisible: false,
                dialogLoading: false,
                nameList: [],
                serverType: _dataDic.get('serverType'),
                saleType: _dataDic.get('saleType'),
                unitType: _dataDic.get('unitType'),
                model: {
                    rules: {
                        name: [
                            { required: true, message: '请选择种植工艺', trigger: 'change' }
                        ],
                        level: [
                            { required: true, message: '请输入等级', trigger: 'change' },
                            { pattern: /(^[1-9]\d*$)/, message: '请输入正整数', trigger: 'change' }
                        ],
                        roleName: [
                            { required: true, message: '请输入角色名', trigger: 'change' }
                        ],
                        number: [
                            { required: true, message: '请输入数量', trigger: 'change' },
                            { pattern: /(^[1-9]\d*$)/, message: '0或正整数', trigger: 'change' }
                        ],
                        price: [
                            { required: true, message: '请输入价格', trigger: 'change' },
                            { pattern: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/, message: '格式不正确', trigger: 'change' }
                        ],
                        operateNumber: [
                            { required: true, message: '请输入删除密钥', trigger: 'change' }
                        ],
                    },
                    dataInfo: {
                        name: '',
                        number: 1,
                        level: 83,
                        roleName: '',
                        serverName: '国际服',
                        sellOrBuy: '卖',
                        price: 1,
                        unit: 'c',
                        operateNumber: '',
                    },
                },
            }
        },
        methods: {
            getNameList() {
                if (this.nameList.length) return

                _ajax.GET(`/seed/affix`, {}, {
                    success: function (res) {
                        this.nameList = res
                    }.bind(this)
                })
            },
            onDialogCancel() {
                this.$refs['addOne'].resetFields()

                this.dialogVisible = false
            },
            onDialogOk() {
                this.$refs['addOne'].validate(function (valid) {
                    if (valid) {
                        var dataInfo = this.model.dataInfo

                        this.dialogLoading = true

                        _ajax.POST(`/seed`, [dataInfo], {
                            complete: function () {
                                this.dialogLoading = false
                            }.bind(this),

                            success: function (res) {
                                _pub.Notify(this, { title: '单个添加', message: res })

                                _loacalStorage.set('baseInfo', { roleName: dataInfo.roleName, operateNumber: dataInfo.operateNumber })

                                this.onDialogCancel()

                                _list.render()
                            }.bind(this)
                        })
                    }
                }.bind(this))
            },
        },
    })


})(window._addOne = {})