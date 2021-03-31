# yumemi-task
# UI
![4euyw-cr21r](https://user-images.githubusercontent.com/65647834/112303114-43526100-8cdf-11eb-99db-62614d347fd8.gif)
# このアプリについて
### リスト画面  
一覧表示されている、Contributorsの名前をタップするとNavigation遷移とRoomにデータ保存。  
### 詳細画面
タップした、Contributorsの詳細を表示する。  
### 履歴画面
閲覧したContributorsをid順に一覧表示する。  
また、タップすることで詳細を見ることもできる

# 使用した技術等
Navigation Component  
ViewModel  
LiveData  
DataBinding  
Retrofit  
Room  
Recycler View  
GithubAPI  
スコープ関数 

# 苦労した点
・RoomDBのプライマリーキーをString型にしていたのを気づかずに、1日エラー原因を探す羽目になったこと

