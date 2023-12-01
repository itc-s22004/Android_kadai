package jp.ac.it_college.std.s22004.androidkadai


data class City(
    val name: String,
)
//val cityList = listOf(
//    City("京都","Kyoto"),
//    City("大阪", "Osaka"),
//    City("沖縄", "Naha"),
//    City("大津","Otu"),
//    City("愛知","Aichi"),
//    City("神奈川","Kanagawa"),
//    City("新潟","Niigata"),
//)

val cityList = listOf(
    City("Hokkaido"),
    City("Aomori"),
    City("Iwate"),
    City("Miyagi"),
    City("Akita"),
    City("Yamagata"),
)

data class City2(
    val id: Int,
    val name: String
)

val city2List = listOf(
    City2( 2130037 , "北海道(札幌)"),
    City2( 2130658 , "青森"),
    City2( 2111834 , "岩手(盛岡)"),
    City2( 2111149 , "宮城(仙台)"),
    City2( 2113719 , "秋田"),
    City2(  2110556, "山形"),
//    City2(  2112923, "福島"),
)

val city3List = mapOf(
    2130037 to "北海道(札幌)",
    2130658 to "青森",
    2111834 to "岩手(盛岡)",
    2111149 to "宮城(仙台)",
    2113014 to "Tokyo"
)







//2111901	茨城(水戸)
//1849053	栃木(宇都宮)
//1857843	群馬(前橋)
//1853226	埼玉(さいたま)