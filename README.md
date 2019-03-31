# lab3_UITest
this is the UI test

## 实验内容：
Android实验三_UI组件实验，本代码创建了Android工程按要求实现四种UI组件。
![Image text](https://github.com/Apple-Jobs/img-folder/blob/master/ui.png)<br>

## 1.利用Android ListView实现要求界面
### 关键代码：

```
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);
        listView = (ListView) findViewById(R.id.listView);
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < name.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("name", name[i]);
            listItem.put("images", images[i]);
            listItems.add(listItem);
        }
/*SimpleAdapter的参数说明
		 * 第一个参数 表示访问整个android应用程序接口，基本上所有的组件都需要
		 * 第二个参数表示生成一个Map(String ,Object)列表选项
		 * 第三个参数表示界面布局的id  表示该文件作为列表项的组件
		 * 第四个参数表示该Map对象的哪些key对应value来生成列表项
		 * 第五个参数表示来填充的组件 Map对象key对应的资源一依次填充组件 顺序有对应关系
		 * 注意的是map对象可以key可以找不到 但组件的必须要有资源填充  因为 找不到key也会返回null 其实就相当于给了一个null资源
		 * 下面的程序中如果 new String[] { "name", "head", "desc","name" } new int[] {R.id.name,R.id.head,R.id.desc,R.id.head}
		 * 这个head的组件会被name资源覆盖
		 * */

        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.item,
                new String[]{"name", "images"}, new int[] {R.id.name, R.id.images});
        listView.setAdapter(adapter);
    }
```
### 实验结果：
![Image text](https://github.com/Apple-Jobs/img-folder/blob/master/ui1.png)<br>
### 问题：
开始时由于命名与SimpleAdapter相同而类中找不到方法，已解决。

## 2.创建自定义布局的AlertDialog
### 关键代码：

```
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        button = (Button)findViewById(R.id.alertDialog) ;
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alterdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.create().show();
    }
```
### 实验结果：
![Image text](https://github.com/Apple-Jobs/img-folder/blob/master/ui2.png)<br>


## 3.使用XML定义菜单
### 关键代码：
```
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlmenu);
        menutv = (TextView)findViewById(R.id.menutv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.font_10:
                menutv.setTextSize(10*2);
                break;
            case R.id.font_16:
                menutv.setTextSize(32);
                break;
            case R.id.font_20:
                menutv.setTextSize(40);
                break;
            case R.id.normal_menu:
                Toast tost = Toast.makeText(XMLMenu.this, "这是普通单击项", Toast.LENGTH_SHORT);
                tost.show();
                break;
            case R.id.font_red:
                menutv.setTextColor(Color.RED);
                break;
            case R.id.font_black:
                menutv.setTextColor(Color.BLACK);
                break;
        }
        return true;
    }
```
### 实验结果：
![Image text](https://github.com/Apple-Jobs/img-folder/blob/master/ui3.png)<br>
![Image text](https://github.com/Apple-Jobs/img-folder/blob/master/ui31.png)<br>
![Image text](https://github.com/Apple-Jobs/img-folder/blob/master/ui32.png)<br>
### 问题：
代码写到页面创建前导致程序无法运行，已解决。

## 4.创建上下文操作模式(ActionMode)的上下文菜单
### 关键代码：
```
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode_work);
        init();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.contex_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_delete:
                        listItems.remove(item_id);
                        adapter.notifyDataSetChanged();
                        actionMode.finish();
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
    }

    public void init() {
        listView = (ListView) findViewById(R.id.listView2);
        listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 6; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("number", i);
            listItem.put("images", R.mipmap.ic_launcher);
            listItems.add(listItem);
        }
        adapter = new SimpleAdapter(this, listItems, R.layout.item2,
                new String[]{"number", "images"}, new int[]{R.id.number, R.id.img});
        listView.setAdapter(adapter);
        //registerForContextMenu(listView);
    }



//    @Override
//    public  void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
//        item_id = info.position;
//        MenuInflater inflater = new MenuInflater(this);
//        inflater.inflate(R.menu.contex_menu, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_delete:
//                listItems.remove(item_id);
//                adapter.notifyDataSetChanged();
//                break;
//        }
//        return true;
//    }
```
### 实验结果：
![Image text](https://github.com/Apple-Jobs/img-folder/blob/master/u4.png)<br>
