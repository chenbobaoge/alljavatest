package org.bobochen.dal.tool;

public class SqlParam {

    public SqlParam() {
    }

    public SqlParam(Object obj, JDBCEnum type) {
        this.obj = obj;
        this.type = type;
    }

    private Object obj ;
    private JDBCEnum type;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public JDBCEnum getType() {
        return type;
    }

    public void setType(JDBCEnum type) {
        this.type = type;
    }


}
