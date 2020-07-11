(function (onwer) {


    onwer.Notify = function (_this, params) {
        _this.$notify({
            title: params.title || '提示',
            message: params.message,
            type: params.type || 'success',
            position: params.position || 'top-left',
        });
    }


})(window._pub = {})