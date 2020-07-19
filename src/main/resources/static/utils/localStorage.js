(function (owner) {

    owner.set = function (type, data) {
        localStorage.setItem(type, JSON.stringify(data))
    }

    owner.get = function (type) {
        var data = localStorage.getItem(type)
        return data ? JSON.parse(data) : {}
    }

    owner.remove = function (type) {
        localStorage.removeItem(type)
    }

})(window._loacalStorage = {})