(function (onwer) {

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

    onwer.Notify = function (_this, params) {
        _this.$notify({
            title: params.title || '提示',
            message: params.message,
            type: params.type || 'success',
            position: params.position || 'top-left',
            duration: params.duration || 3000,
        });
    }

    onwer.Copy = function (_this, value) {
        this.Notify(_this, { title: '复制', message: '您复制了一条种植工艺密钥！' })

        var oInput = document.createElement('input')
        oInput.setAttribute('id', 'copy')
        oInput.value = value
        document.getElementsByTagName('body')[0].appendChild(oInput)

        document.getElementById('copy').select()
        document.execCommand('Copy')
        document.getElementById('copy').remove()
    }

    onwer.IsLogin = function () {
        var isLogin = !!this.GetObjProperty(_loacalStorage.get('userInfo'), 'id')

        if (!isLogin) this.GotoLogin()

        return isLogin
    }

    onwer.GotoLogin = function () {
        _loacalStorage.remove('userInfo')

        window.location.href = 'pages/login/index.html'
    }

})(window._pub = {})