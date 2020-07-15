(function (owner) {

    owner.URL_ROOT = '/seedTrade'

    owner.GET = function (url, data, callback, params) {
        this.AJAX('get', url, data, callback, params)
    }

    owner.POST = function (url, data, callback, params) {
        data = JSON.stringify(data)

        this.AJAX('post', url, data, callback, params)
    }

    owner.PUT = function (url, data, callback, params) {
        this.AJAX('put', url, data, callback, params)
    }

    owner.DELETE = function (url, data, callback, params) {
        this.AJAX('delete', url, data, callback, params)
    }


    owner.AJAX = function (urlType, url, data, callback, params) {
        var _url = this.URL_ROOT + url;

        $.ajax({
            type: urlType,
            url: _url,
            contentType: _pub.GetObjProperty(params, 'contentType') || 'application/json',
            data: data,
            complete: function (res) {
                _pub.GetObjProperty(callback, 'complete') && callback.complete()
            },
            success: function (res) {
                if (res.code === 200) {
                    if (res.success) {
                        _pub.GetObjProperty(callback, 'success') && callback.success(res)
                    } else {
                        _pub.Notify(this, { type: 'warning', title: params.notifyTitle, message: _pub.GetObjProperty(res, 'msg') })
                    }
                } else {
                    _pub.GetObjProperty(callback, 'success') && callback.success(res)
                }
            }.bind(this),
        })
    }

})(window._ajax = {})