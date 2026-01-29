# クイックデプロイガイド / Quick Deploy Guide

**Repository**: https://github.com/skdhc592/employee-management

最速でデプロイする方法を紹介します！

---

## 🚀 最速デプロイ: Railway (5分)

### Step 1: 準備
```bash
cd c:\Users\skdhc\projects\employee-management

# すべての変更をコミット
git add .
git commit -m "Add: Production configuration and deployment files"
git push origin main
```

### Step 2: Railway にアクセス
1. https://railway.app を開く
2. **Login with GitHub** をクリック
3. リポジトリへのアクセスを許可

### Step 3: デプロイ
1. **New Project** をクリック
2. **Deploy from GitHub repo** を選択
3. `employee-management` を選択
4. **Deploy Now** をクリック

### Step 4: PostgreSQL 追加
1. プロジェクト画面で **New** をクリック
2. **Database** → **Add PostgreSQL** を選択
3. 自動的にリンクされます

### Step 5: 環境変数設定
1. アプリケーションサービスをクリック
2. **Variables** タブ:
   ```
   SPRING_PROFILES_ACTIVE=prod
   ```

### Step 6: 設定調整
1. **Settings** タブをクリック
2. **Build Command**: 
   ```
   ./gradlew build -x test
   ```
3. **Start Command**: 
   ```
   java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar build/libs/*.jar
   ```

🎉 **完了！** 数分後にURLが表示されます。

---

## 🐳 Docker でローカルテスト

デプロイ前にDockerでテストできます：

```bash
# Docker Desktop がインストールされていることを確認

# 起動
docker-compose up --build

# ブラウザで確認
# http://localhost:8080/employees

# 停止
docker-compose down
```

---

## 📋 デプロイ前チェックリスト

- [x] PostgreSQL依存関係追加済み (`build.gradle`)
- [x] 本番設定ファイル作成済み (`application-prod.properties`)
- [x] Dockerfile作成済み
- [x] docker-compose.yml作成済み
- [x] Procfile作成済み (Heroku用)
- [ ] すべての変更をGitHubにプッシュ
- [ ] デプロイプラットフォームを選択

---

## 🔗 詳細ガイド

さらに詳しい手順は [DEPLOYMENT.md](DEPLOYMENT.md) をご覧ください。

---

## 💡 ヒント

### データベースの初期データ

`data.sql` の初期データは本番環境でも自動的にロードされます。
不要な場合は、`application-prod.properties` で無効化できます：

```properties
spring.sql.init.mode=never
```

### カスタムドメイン

各プラットフォームでカスタムドメインを設定できます：
- Railway: Settings → Domains
- Render: Settings → Custom Domain
- Heroku: Settings → Domains

### 環境変数の確認

デプロイ後、以下の環境変数が正しく設定されているか確認：
- `DATABASE_URL` - PostgreSQL接続URL
- `SPRING_PROFILES_ACTIVE=prod` - 本番プロファイル
- `PORT` - サーバーポート（自動設定される場合が多い）

---

## 🐛 よくある問題

### 問題: アプリが起動しない

**解決策**:
```bash
# ローカルで本番設定をテスト
SPRING_PROFILES_ACTIVE=prod ./gradlew bootRun
```

### 問題: データベース接続エラー

**解決策**:
- `DATABASE_URL` が正しく設定されているか確認
- PostgreSQLサービスが起動しているか確認

### 問題: ビルドエラー

**解決策**:
```bash
# クリーンビルド
./gradlew clean build
```

---

**ご不明な点があれば、Issues でお気軽にご質問ください！**

https://github.com/skdhc592/employee-management/issues
