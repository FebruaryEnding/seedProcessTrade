(function (onwer) {


    onwer.Notify = function (_this, params) {
        _this.$notify({
            title: params.title || '提示',
            message: params.message,
            type: params.type || 'success',
            position: params.position || 'top-left',
        });
    }

    onwer.GetObjProperty = function (obj, name, returnVal) {
        returnVal = returnVal || ''
        var val = obj ? (obj.hasOwnProperty(name) ? obj[name] : returnVal) : returnVal
        return (val || val === 0) ? val : returnVal
    };

    onwer.IsArray = function (arr) {
        return Array.isArray(arr) ? arr : [];
    };

    onwer.Trim = function (str) {
        return (str === null) ? str : str.replace(/(^\s*)|(\s*$)/g, "");
    };


})(window._pub = {})