/* 公共URL */
var globalURL = 'https://w.bjwinstech.com:8089/';
//var globalURL = 'http://localhost:8089/';
function random(length) {
    var str = Math.random().toString(36).substr(2);
    if (str.length>=length) {
        return str.substr(0, length);
    }
    str += random(length-str.length);
    return str;
}
function resetFile(file,random) {
    if(file!=null){
        //重新创建一个对象  并修改其名字
        var dfl=new File([file],random+file.name,{type:file.type});
        file = dfl;
    }
    return file;
}