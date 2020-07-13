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
                                <el-button @click="onDel(scope.row)" type="text" size="small">del</el-button>
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
                pagination: {
                    page: 1,
                    pageSizes: [10, 20, 50, 100],
                    pageSize: 10,
                    totalCount: 0,
                    layout: "total, sizes, prev, pager, next, jumper",
                },
                dataList: [],
                nameList: [],
                serverType: [
                    {
                        value: '国际服',
                        label: '国际服',
                    },
                    {
                        value: '国服',
                        label: '国服',
                    },
                ],
                saleType: [
                    {
                        value: '卖',
                        label: '卖',
                    },
                    {
                        value: '买',
                        label: '买',
                    },
                ],
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
                        this.dataList = res.rows

                        this.pagination.totalCount = res.totalCount
                    }.bind(this)
                })
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
                _delDialog.show(row.id)
            },
            onMoreAdd() {
                _addMore.show()
            },
            onOneAdd() {
                _addOne.show()
            },
            onCopy(row) {

                _pub.Notify(this, { title: '复制', message: '您复制了一条种植工艺密钥！' })

                _pub.Copy(_pub.GetObjProperty(row, "whisper"))
            },
        },
    })
})(window._list = {})