(function (owner) {

    owner.show = function () {
        vm.dialogVisible = true

        var userInfo = _loacalStorage.get('userInfo')
        vm.model.roleName = _pub.GetObjProperty(userInfo, 'roleName')
        vm.model.serverName = _pub.GetObjProperty(userInfo, 'serverName')
        vm.model.userId = _pub.GetObjProperty(userInfo, 'id')
    }

    var template =
        `
        <div class="add-more">
            <el-dialog title="批量添加" :visible="dialogVisible" width="90%" @close="onDialogCancel">

                <!-- 添加 -->
                <el-form class="addMore-form" :model="model" :rules="model.rules" :inline="true" ref="addMore" label-width="100px">

                    <el-form-item label="买　　卖" prop="sellOrBuy">
                        <el-select style="width: 150px;" v-model="model.sellOrBuy">
                            <el-option v-for="item in saleType" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-table
                        :data="model.tableData"
                        style="width: 100%;margin-top: 30px;"
                        border
                        >

                        <el-table-column
                        fixed
                        width="100">
                        <template slot-scope="scope">
                            <span>{{scope.row.type}}</span>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="冰"
                        width="300">
                        <template slot-scope="scope">
                            <el-form-item :prop="'tableData.' + scope.$index + '.bing.number'" :rules='model.rules.number'>
                                <el-input style="width: 100px" type="number" v-model="scope.row.bing.number" placeholder="数量"></el-input>
                            </el-form-item>

                            <el-form-item :prop="'tableData.' + scope.$index + '.bing.price'" :rules='model.rules.price'>
                                <el-input style="width: 160px;" type="number" v-model="scope.row.bing.price" placeholder="价格">
                                    <el-select style="width: 60px" slot="append" v-model="scope.row.bing.unit">
                                        <el-option
                                        v-for="item in unitType"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-input>
                            </el-form-item>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="火"
                        width="300">
                        <template slot-scope="scope">
                        <el-form-item :prop="'tableData.' + scope.$index + '.huo.number'" :rules='model.rules.number'>
                        <el-input style="width: 100px" type="number" v-model="scope.row.huo.number" placeholder="数量"></el-input>
                        </el-form-item>

                        <el-form-item :prop="'tableData.' + scope.$index + '.huo.price'" :rules='model.rules.price'>
                        <el-input style="width: 160px;" type="number" v-model="scope.row.huo.price" placeholder="价格">
                            <el-select style="width: 60px" slot="append" v-model="scope.row.huo.unit">
                                <el-option
                                v-for="item in unitType"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                                </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="电"
                        width="300">
                        <template slot-scope="scope">
                        <el-form-item :prop="'tableData.' + scope.$index + '.dian.number'" :rules='model.rules.number'>
                        <el-input style="width: 100px" type="number" v-model="scope.row.dian.number" placeholder="数量"></el-input>
                        </el-form-item>

                        <el-form-item :prop="'tableData.' + scope.$index + '.dian.price'" :rules='model.rules.price'>
                        <el-input style="width: 160px;" type="number" v-model="scope.row.dian.price" placeholder="价格">
                            <el-select style="width: 60px" slot="append" v-model="scope.row.dian.unit">
                            <el-option
                            v-for="item in unitType"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="混沌"
                        width="300">
                        <template slot-scope="scope">
                        <el-form-item :prop="'tableData.' + scope.$index + '.hundun.number'" :rules='model.rules.number'>
                        <el-input style="width: 100px" type="number" v-model="scope.row.hundun.number" placeholder="数量"></el-input>
                        </el-form-item>

                        <el-form-item :prop="'tableData.' + scope.$index + '.hundun.price'" :rules='model.rules.price'>
                        <el-input style="width: 160px;" type="number" v-model="scope.row.hundun.price" placeholder="价格">
                            <el-select style="width: 60px" slot="append" v-model="scope.row.hundun.unit">
                            <el-option
                            v-for="item in unitType"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="物理"
                        width="300">
                        <template slot-scope="scope">
                        <el-form-item :prop="'tableData.' + scope.$index + '.wuli.number'" :rules='model.rules.number'>
                        <el-input style="width: 100px" type="number" v-model="scope.row.wuli.number" placeholder="数量"></el-input>
                        </el-form-item>

                        <el-form-item :prop="'tableData.' + scope.$index + '.wuli.price'" :rules='model.rules.price'>
                        <el-input style="width: 160px;" type="number" v-model="scope.row.wuli.price" placeholder="价格">
                            <el-select style="width: 60px" slot="append" v-model="scope.row.wuli.unit">
                            <el-option
                            v-for="item in unitType"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="攻击"
                        width="300">
                        <template slot-scope="scope">
                        <el-form-item :prop="'tableData.' + scope.$index + '.gongji.number'" :rules='model.rules.number'>
                        <el-input style="width: 100px" type="number" v-model="scope.row.gongji.number" placeholder="数量"></el-input>
                        </el-form-item>

                        <el-form-item :prop="'tableData.' + scope.$index + '.gongji.price'" :rules='model.rules.price'>
                        <el-input style="width: 160px;" type="number" v-model="scope.row.gongji.price" placeholder="价格">
                            <el-select style="width: 60px" slot="append" v-model="scope.row.gongji.unit">
                            <el-option
                            v-for="item in unitType"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="防御"
                        width="300">
                        <template slot-scope="scope">
                        <el-form-item :prop="'tableData.' + scope.$index + '.fangyu.number'" :rules='model.rules.number'>
                        <el-input style="width: 100px" type="number" v-model="scope.row.fangyu.number" placeholder="数量"></el-input>
                        </el-form-item>

                        <el-form-item :prop="'tableData.' + scope.$index + '.fangyu.price'" :rules='model.rules.price'>
                        <el-input style="width: 160px;" type="number" v-model="scope.row.fangyu.price" placeholder="价格">
                            <el-select style="width: 60px" slot="append" v-model="scope.row.fangyu.unit">
                            <el-option
                            v-for="item in unitType"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="法术"
                        width="300">
                        <template slot-scope="scope">
                        <el-form-item :prop="'tableData.' + scope.$index + '.fashu.number'" :rules='model.rules.number'>
                        <el-input style="width: 100px" type="number" v-model="scope.row.fashu.number" placeholder="数量"></el-input>
                        </el-form-item>

                        <el-form-item :prop="'tableData.' + scope.$index + '.fashu.price'" :rules='model.rules.price'>
                        <el-input style="width: 160px;" type="number" v-model="scope.row.fashu.price" placeholder="价格">
                            <el-select style="width: 60px" slot="append" v-model="scope.row.fashu.unit">
                            <el-option
                            v-for="item in unitType"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="速度"
                        width="300">
                        <template slot-scope="scope">
                        <el-form-item :prop="'tableData.' + scope.$index + '.sudu.number'" :rules='model.rules.number'>
                        <el-input style="width: 100px" type="number" v-model="scope.row.sudu.number" placeholder="数量"></el-input>
                        </el-form-item>

                        <el-form-item :prop="'tableData.' + scope.$index + '.sudu.price'" :rules='model.rules.price'>
                        <el-input style="width: 160px;" type="number" v-model="scope.row.sudu.price" placeholder="价格">
                            <el-select style="width: 60px" slot="append" v-model="scope.row.sudu.unit">
                            <el-option
                            v-for="item in unitType"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="暴击"
                        width="300">
                        <template slot-scope="scope">
                        <el-form-item :prop="'tableData.' + scope.$index + '.baoji.number'" :rules='model.rules.number'>
                        <el-input style="width: 100px" type="number" v-model="scope.row.baoji.number" placeholder="数量"></el-input>
                        </el-form-item>

                        <el-form-item :prop="'tableData.' + scope.$index + '.baoji.price'" :rules='model.rules.price'>
                        <el-input style="width: 160px;" type="number" v-model="scope.row.baoji.price" placeholder="价格">
                            <el-select style="width: 60px" slot="append" v-model="scope.row.baoji.unit">
                            <el-option
                            v-for="item in unitType"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>
                        </template>
                        </el-table-column>

                        <el-table-column
                        label="势力词缀"
                        width="300">
                        <template slot-scope="scope">
                        <el-form-item :prop="'tableData.' + scope.$index + '.slcz.number'" :rules='model.rules.number'>
                        <el-input style="width: 100px" type="number" v-model="scope.row.slcz.number" placeholder="数量"></el-input>
                        </el-form-item>

                        <el-form-item :prop="'tableData.' + scope.$index + '.slcz.price'" :rules='model.rules.price'>
                        <el-input style="width: 160px;" type="number" v-model="scope.row.slcz.price" placeholder="价格">
                            <el-select style="width: 60px" slot="append" v-model="scope.row.slcz.unit">
                            <el-option
                            v-for="item in unitType"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                            </el-select>
                        </el-input>
                    </el-form-item>
                        </template>
                        </el-table-column>
                    </el-table>
                    </el-form>

                    <span slot="footer" class="dialog-footer">
                        <el-button @click="onDialogCancel">取 消</el-button>
                        <el-button type="primary" :loading="dialogLoading" @click="onDialogOk">确 定</el-button>
                    </span>
            </el-dialog>
        </div>
    `

    var vm = new Vue({
        el: '.add-more',
        template: template,
        data() {
            return {
                dialogVisible: false,
                dialogLoading: false,
                serverType: _dataDic.get('serverType'),
                saleType: _dataDic.get('saleType'),
                unitType: _dataDic.get('unitType'),
                model: {
                    rules: {
                        number: [
                            { required: true, message: '', trigger: 'change' },
                            { pattern: /^(0|\+?[1-9][0-9]*)$/, message: '', trigger: 'change' }
                        ],
                        price: [
                            { required: true, message: '', trigger: 'change' },
                            { pattern: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/, message: '', trigger: 'change' }
                        ],
                    },
                    serverName: '',
                    sellOrBuy: '卖',
                    roleName: '',
                    userId: '',
                    tableData: [
                        {
                            type: '去',
                            typeKey: 'remove',
                            bing: {
                                name: "冰",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            huo: {
                                name: "火",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            dian: {
                                name: "电",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            hundun: {
                                name: "混沌",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            wuli: {
                                name: "物理",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            gongji: {
                                name: "攻击",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            fangyu: {
                                name: "防御",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            fashu: {
                                name: "法术",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            sudu: {
                                name: "速度",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            baoji: {
                                name: "暴击",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            slcz: {
                                name: "势力词缀",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                        },
                        {
                            type: '加',
                            typeKey: 'add',
                            bing: {
                                name: "冰",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            huo: {
                                name: "火",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            dian: {
                                name: "电",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            hundun: {
                                name: "混沌",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            wuli: {
                                name: "物理",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            gongji: {
                                name: "攻击",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            fangyu: {
                                name: "防御",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            fashu: {
                                name: "法术",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            sudu: {
                                name: "速度",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            baoji: {
                                name: "暴击",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            slcz: {
                                name: "势力词缀",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                        },
                        {
                            type: '幸运加',
                            typeKey: 'addLuck',
                            bing: {
                                name: "冰",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            huo: {
                                name: "火",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            dian: {
                                name: "电",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            hundun: {
                                name: "混沌",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            wuli: {
                                name: "物理",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            gongji: {
                                name: "攻击",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            fangyu: {
                                name: "防御",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            fashu: {
                                name: "法术",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            sudu: {
                                name: "速度",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            baoji: {
                                name: "暴击",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            slcz: {
                                name: "势力词缀",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                        },
                        {
                            type: '去加',
                            typeKey: 'removeAndAdd',
                            bing: {
                                name: "冰",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            huo: {
                                name: "火",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            dian: {
                                name: "电",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            hundun: {
                                name: "混沌",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            wuli: {
                                name: "物理",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            gongji: {
                                name: "攻击",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            fangyu: {
                                name: "防御",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            fashu: {
                                name: "法术",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            sudu: {
                                name: "速度",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            baoji: {
                                name: "暴击",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            slcz: {
                                name: "势力词缀",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                        },{
                            type: '非加',
                            typeKey: 'notAndAdd',
                            bing: {
                                name: "冰",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            huo: {
                                name: "火",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            dian: {
                                name: "电",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            hundun: {
                                name: "混沌",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            wuli: {
                                name: "物理",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            gongji: {
                                name: "攻击",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            fangyu: {
                                name: "防御",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            fashu: {
                                name: "法术",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            sudu: {
                                name: "速度",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            baoji: {
                                name: "暴击",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                            slcz: {
                                name: "势力词缀",
                                number: 0,
                                price: 35,
                                unit: 'c',
                            },
                        }
                    ]
                },
            }
        },
        methods: {
            onDialogOk() {
                this.$refs['addMore'].validate(function (valid) {
                    if (valid) {
                        var subData = {
                            roleName: this.model.roleName,
                            serverName: this.model.serverName,
                            sellOrBuy: this.model.sellOrBuy,
                            userId: this.model.userId
                        }

                        this.model.tableData.forEach(function (item) {
                            var arr = []

                            for (var key in item) {
                                if (key === 'type' || key === 'typeKey') continue

                                arr.push(item[key])
                            }

                            subData[item.typeKey] = arr
                        })

                        this.dialogLoading = true

                        _ajax.POST(`/seed/mulAdd`, subData, {
                            complete: function () {
                                this.dialogLoading = false
                            }.bind(this),
                            success: function (res) {

                                _pub.Notify(this, { title: '批量添加', message: _pub.GetObjProperty(res, 'msg') })

                                this.onDialogCancel()

                                _list.render()
                            }.bind(this),

                            warning: function (res) {
                                _pub.Notify(this, { type: 'warning', title: '批量添加', message: _pub.GetObjProperty(res, 'msg') })
                            }.bind(this)
                        })
                    }
                }.bind(this));
            },
            onDialogCancel() {
                this.$refs['addMore'].resetFields()
                this.dialogVisible = false
            },
        },
    })

})(window._addMore = {})