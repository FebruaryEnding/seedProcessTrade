(function (owner) {

    owner.render = function () {
        vm.onSearch()
    }

    var template =
        `
            <div class="list-box">
                <div class="search-box">
                    <div class="search-con">
                        <el-input class="search-item search-input" placeholder="种植工艺 / 角色名" suffix-icon="el-icon-search"
                            v-model="searchInfo.keyValue" @change="onSearch">
                        </el-input>
                        <el-select class="search-item" v-model="searchInfo.serverName" clearable @change="onSearch"
                            placeholder="请选择服务器">
                            <el-option v-for="item in serverType" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select class="search-item" v-model="searchInfo.sellOrBuy" clearable @change="onSearch"
                            placeholder="请选择买卖类型">
                            <el-option v-for="item in saleType" :key="item.value" :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </div>

                    <div class="add-con">
                        <el-button class="btn" icon="el-icon-plus" type="primary" @click="onMoreAdd">批量添加</el-button>
                        <el-button class="btn" icon="el-icon-plus" type="primary" @click="onOneAdd">单个添加</el-button>
                    </div>
                </div>

                <!-- 列表 -->
                <div class="list-box">
                    <el-table :data="dataList" style="width: 100%">
                        <el-table-column prop="createdTime" label="创建时间" min-width="160">
                        </el-table-column>

                        <el-table-column prop="name" label="种植工艺" min-width="300">
                            <template slot-scope="scope">
                                <el-popover placement="right" title="种植工艺" width="200" trigger="hover">
                                    <p>{{scope.row.name}}</p>
                                    <div slot="reference" class="overHid-1">
                                        <span>{{scope.row.name}}</span>
                                    </div>
                                </el-popover>
                            </template>
                        </el-table-column>

                        <el-table-column prop="roleName" label="角色名" min-width="200">
                        </el-table-column>

                        <el-table-column prop="level" label="等级">
                        </el-table-column>

                        <el-table-column prop="price" label="价格">
                            <template slot-scope="scope">
                                <span>{{scope.row.price}}/{{scope.row.unit}}</span>
                            </template>
                        </el-table-column>

                        <el-table-column prop="number" label="数量">
                        </el-table-column>

                        <el-table-column prop="sellOrBuy" label="买卖">
                        </el-table-column>

                        <el-table-column prop="serverName" label="服务器">
                        </el-table-column>

                        <el-table-column fixed="right" label="操作" width="100">
                            <template slot-scope="scope">
                                <el-button @click="onCopy(scope.row)" type="text" size="small">copy</el-button>

                                <el-popover
                                placement="top"
                                width="160"
                                trigger="hover">
                                <p>您确认删除该条数据吗？</p>
                                <div style="text-align: right; margin: 0">
                                    <el-button type="primary" size="mini" :loading="delLoading" @click="onDel(scope.row)">确定</el-button>
                                </div>
                                <el-button v-if="verDel(scope.row)" type="text" size="small" slot="reference">del</el-button>
                                </el-popover>
                            </template>
                        </el-table-column>

                    </el-table>
                </div>

                <!-- 分页 -->
                <div class="pagination-box">
                    <el-pagination 
                        background 
                        @size-change="onHandleSizeChange"
                        @current-change="onPaginationChange" 
                        :layout="pagination.layout"
                        :page-sizes="pagination.pageSizes"
                        :page-size="pagination.pageSize"
                        :total="pagination.totalCount"
                        :hide-on-single-page="true">
                    </el-pagination>
                </div>
            </div>
        `

    var vm = new Vue({
        el: '.list-box',
        template: template,
        data() {
            return {
                searchInfo: {
                    keyValue: '',
                    serverName: '',
                    sellOrBuy: '',
                },
                pagination: _dataDic.get('pagination'),
                serverType: _dataDic.get('serverType'),
                saleType: _dataDic.get('saleType'),
                dataList: [],
                nameList: [],
                userInfo: _loacalStorage.get('userInfo'),
                delLoading: false,
            }
        },
        mounted() {
            // 获取列表
            this.getList()
        },
        methods: {
            getList() {
                var searchInfo = this.searchInfo
                searchInfo.page = this.pagination.page
                searchInfo.pageSize = this.pagination.pageSize

                _ajax.GET(`/seed`, searchInfo, {
                    success: function (res) {
                        this.dataList = _pub.IsArray(_pub.GetObjProperty(res, 'rows'))

                        this.pagination.totalCount = res.totalCount
                    }.bind(this)
                })
            },
            verDel(row) {
                return _pub.GetObjProperty(row, 'roleName') === _pub.GetObjProperty(this.userInfo, 'roleName')
            },
            onHandleSizeChange(val) {
                this.pagination.pageSize = val

                this.getList()
            },
            onPaginationChange(val) {
                this.pagination.page = val

                this.getList()
            },
            onSearch() {
                this.pagination.page = 1

                this.getList()
            },
            onDel(row) {
                if (_pub.IsLogin()) {
                    this.delLoading = true

                    _ajax.DELETE(`/seed/${_pub.GetObjProperty(row, 'id')}/${_pub.GetObjProperty(this.userInfo, 'id')}`, {}, {
                        complete: function () {
                            this.delLoading = false
                        }.bind(this),

                        success: function (res) {
                            _pub.Notify(this, { title: '删除', message: _pub.GetObjProperty(res, 'msg') })

                            this.onSearch()
                        }.bind(this),

                        warning: function (res) {
                            _pub.Notify(this, { type: 'warning', title: '删除', message: _pub.GetObjProperty(res, 'msg') })
                        }.bind(this)
                    })
                }
            },
            onMoreAdd() {
                if (_pub.IsLogin()) _addMore.show()
            },
            onOneAdd() {
                if (_pub.IsLogin()) _addOne.show()
            },
            onCopy(row) {
                if (_pub.IsLogin()) _pub.Copy(this, _pub.GetObjProperty(row, "whisper"))
            },
        },
    })
})(window._list = {})