# structure
- item_single_textview.xml: 设置每个元素的显示
- -> MyViewholder: 定义 view 中的 元件
- -> SimpleAdapter: 控制元素的显示
    - onCreateView: 创建的时候是怎样显示的 -> 最主要inflate view
    - onBindViewHolder: 数据更改的操作 -> 最主要的view里元素的设置
- -> MainActivity: 设置 adapter 和 layout <- activity_main.xml
    - 初始化和set adapter
    - 初始化和set layoutManager