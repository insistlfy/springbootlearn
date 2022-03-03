function REGSTATUS(value) {
    if ("" === value || null == value) {
        result = value;
    }
    if (value.indexOf("在营") !== -1 || value.indexOf("存续") !== -1 || value.indexOf("存活") !== -1 || value.indexOf("开业") !== -1 || value.indexOf("正常") !== -1 || value.indexOf("在册") !== -1 || value.indexOf("成立") !== -1) {
        result = "01";
    } else if (value.indexOf("吊销") !== -1) {
        result = "02";
    } else if (value.indexOf("注销") !== -1 || value.indexOf("清算") !== -1 || value.indexOf("停业") !== -1) {
        result = "03";
    } else if (value.indexOf("迁出") !== -1) {
        result = "04";
    } else {
        result = "05";
    }
}

function ENTTYPECODE(value) {
    if ("" === value || null == value) {
        result = value;
    }
    if (value.indexOf("有限责任公司") !== -1) {
        result = "10";
    } else if (value.indexOf("股份有限公司") !== -1 || value.indexOf("股份合作") !== -1 || value.indexOf("股份合作") !== -1 || value.indexOf("股份制") !== -1) {
        result = "20";
    } else if (value.indexOf("国企") !== -1) {
        result = "30";
    } else if (value.indexOf("外商投资") !== -1 || (value.indexOf("外国") !== -1 && value.indexOf("投资") !== -1) || value.indexOf("港澳台") !== -1 || value.indexOf("台港澳") !== -1 || value.indexOf("中外") !== -1) {
        result = "40";
    } else if (value.indexOf("个人独资") !== -1) {
        result = "50";
    } else if (value.indexOf("合伙制") !== -1) {
        result = "60";
    } else if (value.indexOf("个体") !== -1) {
        result = "70";
    } else if (value.indexOf("联营") !== -1) {
        result = "80";
    } else if (value.indexOf("集体") !== -1) {
        result = "90";
    } else if (value.indexOf("特殊普通合伙") !== -1 || value.indexOf("有限合伙") !== -1) {
        result = "100";
    } else if (value.indexOf("合伙企业") !== -1 || value.indexOf("普通合伙") !== -1) {
        result = "110";
    } else {
        result = "99";
    }
    console.log(result);
}

function FROZENSTATUS(value) {
    if ("" === value || null == value) {
        result = value;
    }
    if (value.indexOf("冻结") !== -1 && value.indexOf("解除冻结") !== -1) {
        result = "02";
    } else if (value.indexOf("冻结") !== -1) {
        result = "01";
    } else if (value.indexOf("解除冻结") !== -1) {
        result = "02";
    } else {
        result = "03";
    }

}

function REGCAPCUR(value) {
    if ("" === value || null == value) {
        result = value;
    }
    if (value.indexOf("万") !== -1 || value.indexOf("万元") !== -1 || value.indexOf("人民币元") !== -1 || value.indexOf("万元人民币") !== -1 || value.indexOf("元人民币") !== -1 || value.indexOf("元") !== -1) {
        result = "01";
    } else {
        result = "02";
    }

    console.log(result);
}

function ALTITEM(value) {
    if ("" === value || null == value) {
        result = value;
    }
    if (value.indexOf("名称") !== -1) {
        result = "01";
    } else if (value.indexOf("企业类型") !== -1 || value.indexOf("机构类型") !== -1) {
        result = "02";
    } else if (value.indexOf("股东") !== -1 || value.indexOf("出资人") !== -1 || value.indexOf("股权") !== -1 || value.indexOf("投资人") !== -1) {
        result = "03";
    } else if (value.indexOf("负责人") !== -1 || value.indexOf("法定代表人") !== -1 || value.indexOf("代表") !== -1 || value.indexOf("执行人") !== -1 || value.indexOf("个体经营者") !== -1 || value.indexOf("投资人") !== -1 || value.indexOf("合伙事务执行人") !== -1) {
        result = "04";
    } else if (value.indexOf("管理人员") !== -1 || value.indexOf("董事") !== -1 || value.indexOf("监事") !== -1 || value.indexOf("经理") !== -1) {
        result = "05";
    } else if (value.indexOf("注册资本") !== -1 || value.indexOf("注册资金") !== -1 || value.indexOf("出资总额") !== -1 || value.indexOf("认缴资本") !== -1 || value.indexOf("出资额") !== -1 || value.indexOf("投资总额") !== -1) {
        result = "06";
    } else if (value.indexOf("实收资本") !== -1) {
        result = "07";
    } else if (value.indexOf("出资方式") !== -1) {
        result = "08";
    } else if (value.indexOf("出资日期") !== -1) {
        result = "09";
    } else if (value.indexOf("出资比例") !== -1) {
        result = "10";
    } else if (value.indexOf("经营期限") !== -1 || value.indexOf("营业期限") !== -1 || value.indexOf("驻在期限") !== -1 || value.indexOf("合伙期限") !== -1 || value.indexOf("有效期限") !== -1) {
        result = "11";
    } else if (value.indexOf("住所") !== -1 || value.indexOf("场所") !== -1 || value.indexOf("地址") !== -1) {
        result = "12";
    } else if (value.indexOf("行业") !== -1) {
        result = "13";
    } else if (value.indexOf("一般经营项目") !== -1 || value.indexOf("许可经营项目") !== -1) {
        result = "14";
    } else if (value.indexOf("经营范围") !== -1 || value.indexOf("业务范围") !== -1) {
        result = "15";
    } else if (value.indexOf("经营方式") !== -1) {
        result = "16";
    } else if (value.indexOf("登记机关") !== -1) {
        result = "17";
    } else if (value.indexOf("分支") !== -1 || value.indexOf("分公司") !== -1) {
        result = "18";
    } else {
        result = "99";
    }
}


// console.log(REGSTATUS("吊销"));
// console.log(ENTTYPECODE("外国1投资"));
// console.log(FROZENSTATUS("解除冻结"));
// console.log(REGCAPCUR("万元"));
function MoneyRemoveChar(value) {
    if ("" === value || null == value) {
        result = value;
    }
    if (value.indexOf("元人民币") !== -1 || value.indexOf("元") !== -1 || value.indexOf("人民币元") !== -1) {
        result = value.replace(/[^0-9]/ig, "");
    } else {
        result = value;
    }
}

function test1(value) {
    if ("" == value || null == value) {
        result = value
    }
    if (value.indexOf("%") > -1) {
        result = parseFloat(value.replace(/%/g, "").replace(/\s+/g, "")) / 100;
    } else {
        result = value
    }
    console.log(result)
}

function test2(value) {
    if (/^[A-Za-z\s*]+$/.test(value)) {
        result = value;
    } else {
        result = value.replace(/\s/g, "")
    }
    ;


    if ("" == value || null == value) {
        result = value;
    }

    if (value.indexOf(",") > -1 && value.indexOf("，") > -1) {
        value1 = value.replace(/,/g, "");
        value = value1.replace(/，/g, "");
        result = value;
    }

    if (value.indexOf(",") > -1) {
        result = value.replace(/,/g, "");
    } else {
        result = value;
    }

    if (RegExp(",").test(value)) {
        result = value.replace(/,/g, "");
    }

    if (RegExp("，").test(value)) {
        result = value.replace(/，/g, "");
    }
    console.log(result);
}

test1("50.00%");




