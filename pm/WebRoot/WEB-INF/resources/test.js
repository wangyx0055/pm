
var numberpanel = new FAPUI.form.FormPanel({
	border : false,
	columns : 4,
	field : field,
	height : 27,
	items : [ {
		allowBlank : true,
		ctype : "combobox",
		data : [ {
			"text" : "等于",
			"value" : "eq"
		}, {
			"text" : "大于",
			"value" : "gt"
		}, {
			"text" : "小于",
			"value" : "lt"
		}, {
			"text" : "小于等于",
			"value" : "le"
		}, {
			"text" : "大于等于",
			"value" : "ge"
		} ],
		fieldLabel : fieldLabel,
		labelWidth : 40,
		labelSplit : ':'
	}, {
		allowBlank : true,
		ctype : "numberfield",
		hideLabel : true
	}, {
		allowBlank : true,
		ctype : "combobox",
		data : [ {
			"text" : "等于",
			"value" : "eq"
		}, {
			"text" : "大于",
			"value" : "gt"
		}, {
			"text" : "小于",
			"value" : "lt"
		}, {
			"text" : "小于等于",
			"value" : "le"
		}, {
			"text" : "大于等于",
			"value" : "ge"
		} ],
		fieldLabel : "到",
		labelAlign : "center",
		labelWidth : 30
	}, {
		allowBlank : true,
		ctype : "numberfield",
		hideLabel : true
	} ],
	getResult : function() {
		var value1 = this.items[1].getValue();
		var value2 = this.items[3].getValue();
		if ((value1 == null || value1 == "")
				&& (value2 == null || value2 == ""))
			return null;
		var o = {};
		o.type = "and";
		o.data = [];

		if (value1 != null && value1 != "") {
			var o1 = {};
			o1.type = "numeric";
			o1.field = this.field;
			o1.op = this.items[0].getValue();
			o1.value = value1;
			o.data.push(o1);
		}

		if (value2 != null && value2 != "") {
			var o2 = {};
			o2.type = "numeric";
			o2.field = this.field;
			o2.op = this.items[2].getValue();
			o2.value = value2;
			o.data.push(o2);
		}
		return o;
	}
});