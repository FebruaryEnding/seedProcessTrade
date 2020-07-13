(function (owner) {

    owner.get = function (key) {
        return warehouse[key]
    }

    var warehouse = {
        serverType: [{ value: '国际服', label: '国际服' }, { value: '国服', label: '国服' }],
        saleType: [{ value: '卖', label: '卖' }, { value: '买', label: '买' }],
        unitType: [{ value: 'c', label: 'c' }, { value: 'e', label: 'e' }],

        pagination: {
            page: 1,
            pageSizes: [10, 20, 50, 100],
            pageSize: 10,
            totalCount: 0,
            layout: "total, sizes, prev, pager, next, jumper",
        },
    }



})(window._dataDic = {})