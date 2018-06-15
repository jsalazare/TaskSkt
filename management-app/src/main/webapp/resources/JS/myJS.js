/**
 * Created by jsalazar on 6/14/18.
 */
function validate()
{
    var a = document.getElementById("name");
    var valid = true;
    if(a.value.length<=0)
    {
        alert("Don't leave the field empty!");
        valid = false;
    }
    else{
        if(isNaN(c)){
            alert("Enter a number");
            valid = false;
        }
    }
    return valid;
};