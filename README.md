# TransparentSectorProgressView
TransparentSectorProgressView 透明的扇形进度条
一般的进度图都是顺时针增加,而这个是实现瞬时间递减的效果
#简单使用
实时看到扇形弧度
tspv_percent 0%~100% 1% = 36°
<com.hy.tspv.Tspv
            android:id="@+id/tspv_1"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:tspv_color="#30000000"
            app:tspv_percent="10" />
 ![demo](/1.pic.jpg)
