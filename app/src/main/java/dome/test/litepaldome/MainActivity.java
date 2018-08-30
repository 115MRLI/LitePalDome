package dome.test.litepaldome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.litepal.LitePal;
import org.w3c.dom.Text;

import java.util.List;

import dome.test.litepaldome.model.UserSql;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText idEdit, accountEdit, nameEdit, passwordEdit;
    private TextView seeAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        getIntentData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        idEdit = findViewById(R.id.id_edit);
        accountEdit = findViewById(R.id.account_edit);
        nameEdit = findViewById(R.id.name_edit);
        passwordEdit = findViewById(R.id.password_edit);
        seeAll = findViewById(R.id.see_all);
        findViewById(R.id.select_button).setOnClickListener(this);
        findViewById(R.id.del_button).setOnClickListener(this);
        findViewById(R.id.update_button).setOnClickListener(this);
        findViewById(R.id.add_button).setOnClickListener(this);
    }

    /**
     * 控件监听
     */
    private void initEvent() {
    }

    /**
     * 数据初始化
     */
    private void getIntentData() {
    }

    @Override
    public void onClick(View v) {
        String id = "";
        switch (v.getId()) {
            case R.id.select_button:
                id = idEdit.getText().toString().trim();
                if (TextUtils.isEmpty(id)) {
                    seeAll.setText(selectAll());
                } else {
                    seeAll.setText(selectForId(id));
                }
                break;
            case R.id.del_button:
                break;
            case R.id.update_button:
                break;
            case R.id.add_button:
                UserSql userSql = new UserSql();
                id = idEdit.getText().toString().trim();
                String account = accountEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();
                String name = nameEdit.getText().toString().trim();

                break;
        }
    }

    /**
     * 查询全部
     *
     * @return
     */
    private String selectAll() {
        List<UserSql> allSongs = LitePal.findAll(UserSql.class);
        if (allSongs.size() == 0) {
            return "啥都没有别查了";
        } else {
            return allSongs.toString();
        }
    }

    /**
     * 按id查询数据
     *
     * @param id 主键
     * @return
     */
    private String selectForId(String id) {
        UserSql song = LitePal.find(UserSql.class, Integer.parseInt(id));
        if (song == null) {
            return "你要查的写个没有";
        } else {
            return song.toString();
        }

    }
}
