/**
 * ajax get 请求
 *
 * @param url
 * @param data
 * @param callback
 */
function ajax_doGet(url,data,callback){
    $.get(url,data, function(result){
        if(typeof callback === "function"){
            callback(result);
        }
    });
}

/**
 * ajax post 请求
 *
 * @param url
 * @param data
 * @param callback
 */
function ajax_doPost(url,data,callback){
    $.post(url,data,function(result){
        if(typeof callback === "function"){
            callback(result);
        }
    },"json")
}
