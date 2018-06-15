/**
 * Created by jsalaza   r on 6/14/18.
 */
function validate() {
    var name = document.getElementById("name");
    var length = document.getElementById("length");
    var width = document.getElementById("width");
    var heigth = document.getElementById("heigth");
    var weight = document.getElementById("weight");

    var valid = true;
    if(hasInvalidaLength(name) || hasInvalidaLength(length) || hasInvalidaLength(width) || hasInvalidaLength(heigth) || hasInvalidaLength(weight)) {
        alert("Don't leave the fields empty!");
        valid = false;
    }
    return valid;
};

function hasInvalidaLength(elem) {
    return elem.value.length <= 0;
}