package tablegen;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import lombok.Data;

@Data
public class Table {
    private String name;
    private List<Field> fields;
    private List<Index> indexs;
    
    public String getCreateTableSql() {
        StringBuffer sb = new StringBuffer();
        sb.append("create table ");
        sb.append(name).append(" (");
        sb.append(" id int auto_increment primary key,");
        
        for(Field f : fields) {
            sb.append(f.getName()).append(" ").append(f.getType()).append(",");
        }
        
        if(!CollectionUtils.isEmpty(indexs)) {
            for(Index index : indexs) {
                sb.append(index.getType()).append(" `").append(index.getName()).append("`");
                sb.append(" (");
                for(Field indexField : index.getFields()) {
                    sb.append("`");
                    sb.append(indexField.getName());
                    sb.append("`,");
                }
                sb.deleteCharAt(sb.lastIndexOf(","));
                sb.append(")");
            }
        } else {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append(") ENGINE=InnoDB;");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Table t = new Table();
        t.setName("test");
        List<Field> fs = new ArrayList<>();
        Field f1 = new Field();
        f1.setName("f1");
        f1.setType("int(11)");
        Field f2 = new Field();
        f2.setName("f2");
        f2.setType("varchar(32)");
        fs.add(f1);
        fs.add(f2);
        t.setFields(fs);
        List<Index> indexs = new ArrayList<>();
        Index i = new Index();
        i.setName("f1_f2_i");
        i.setType("KEY");
        i.setFields(fs);
        indexs.add(i);
        t.setIndexs(indexs);
        
        System.out.println(t.getCreateTableSql());
    }
}

