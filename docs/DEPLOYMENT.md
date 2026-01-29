# デプロイガイド / Deployment Guide

**Repository**: https://github.com/skdhc592/employee-management

このガイドでは、Employee Management Systemを各種クラウドプラットフォームにデプロイする方法を説明します。

---

## 📋 目次 / Table of Contents

1. [Render (推奨・無料)](#1-render-推奨無料)
2. [Railway (簡単・無料)](#2-railway-簡単無料)
3. [Heroku (クラシック)](#3-heroku-クラシック)
4. [Docker (汎用)](#4-docker-汎用)
5. [本番環境の設定](#本番環境の設定)

---

## 🚀 デプロイ前の準備

### データベースの変更

現在H2インメモリデータベースを使用していますが、本番環境では永続的なデータベースが必要です。
以下のいずれかを選択してください：

- **PostgreSQL** (推奨)
- **MySQL**
- **H2 (ファイルモード)** - 小規模アプリ向け

---

## 1. Render (推奨・無料)

### ✨ 特徴
- ✅ 無料プラン有り
- ✅ 自動デプロイ
- ✅ PostgreSQL無料枠有り
- ✅ 簡単なセットアップ

### 📝 手順

#### Step 1: Renderアカウント作成
1. https://render.com にアクセス
2. GitHubアカウントでサインアップ

#### Step 2: PostgreSQL用の設定を追加

**`application-prod.properties` を作成**:

```bash
# Create new file
touch src/main/resources/application-prod.properties
```

**ファイル内容**:

```properties
# PostgreSQL Configuration
spring.datasource.url=${DATABASE_URL}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# データベース初期化
spring.sql.init.mode=always
spring.sql.init.encoding=UTF-8

# Production settings
server.port=${PORT:8080}
logging.level.com.example.employee=INFO
```

#### Step 3: PostgreSQL依存関係を追加

**`build.gradle` に追加**:

```gradle
dependencies {
    // 既存の依存関係...
    
    // PostgreSQL for production
    runtimeOnly 'org.postgresql:postgresql'
}
```

#### Step 4: Renderでデプロイ

1. **New +** → **Web Service** を選択
2. GitHubリポジトリを連携
3. 以下の設定:
   - **Name**: `employee-management`
   - **Environment**: `Java`
   - **Build Command**: `./gradlew build -x test`
   - **Start Command**: `java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar build/libs/*.jar`
   - **Plan**: Free

4. **Environment Variables** を追加:
   ```
   SPRING_PROFILES_ACTIVE=prod
   ```

5. **Create Web Service** をクリック

#### Step 5: PostgreSQLデータベースを作成

1. **New +** → **PostgreSQL** を選択
2. 無料プランを選択
3. データベースが作成されたら、Web ServiceにリンクさせるEnvironment Variableを追加:
   - `DATABASE_URL` (自動で設定される)

🎉 デプロイ完了！

---

## 2. Railway (簡単・無料)

### ✨ 特徴
- ✅ 無料枠有り ($5/月のクレジット)
- ✅ 非常に簡単
- ✅ PostgreSQL統合
- ✅ 自動HTTPS

### 📝 手順

#### Step 1: Railway アカウント作成
1. https://railway.app にアクセス
2. GitHubでサインアップ

#### Step 2: プロジェクト作成

1. **New Project** をクリック
2. **Deploy from GitHub repo** を選択
3. `employee-management` を選択

#### Step 3: PostgreSQL追加

1. **New** → **Database** → **Add PostgreSQL**
2. 自動的に `DATABASE_URL` が設定される

#### Step 4: 環境変数設定

1. サービスをクリック
2. **Variables** タブ:
   ```
   SPRING_PROFILES_ACTIVE=prod
   PORT=8080
   ```

#### Step 5: ビルド設定

1. **Settings** タブ
2. **Build Command**: `./gradlew build -x test`
3. **Start Command**: `java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar build/libs/*.jar`

🎉 デプロイ完了！

---

## 3. Heroku (クラシック)

### ⚠️ 注意
Herokuの無料プランは終了しましたが、有料プランは利用可能です。

### 📝 手順

#### Step 1: Heroku CLIインストール
```bash
# Windows
# https://devcenter.heroku.com/articles/heroku-cli からインストーラーをダウンロード
```

#### Step 2: ログインとアプリ作成

```bash
heroku login
heroku create employee-management-your-name
```

#### Step 3: PostgreSQL追加

```bash
heroku addons:create heroku-postgresql:mini
```

#### Step 4: 環境変数設定

```bash
heroku config:set SPRING_PROFILES_ACTIVE=prod
```

#### Step 5: Procfileを作成

```bash
echo "web: java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar build/libs/*.jar" > Procfile
```

#### Step 6: デプロイ

```bash
git add .
git commit -m "Add: Heroku configuration"
git push heroku main
```

🎉 デプロイ完了！

---

## 4. Docker (汎用)

### 📝 Dockerfileを作成

```dockerfile
# Build stage
FROM gradle:8-jdk21 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 📝 docker-compose.yml を作成

```yaml
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/employeedb
      - SPRING_DATASOURCE_USERNAME=postgres
      - SPRING_DATASOURCE_PASSWORD=postgres
    depends_on:
      - db

  db:
    image: postgres:16-alpine
    environment:
      - POSTGRES_DB=employeedb
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

### 🚀 実行

```bash
# ビルドと起動
docker-compose up --build

# バックグラウンドで実行
docker-compose up -d

# 停止
docker-compose down
```

---

## 📝 本番環境の設定

### 必要な設定ファイル

#### 1. `application-prod.properties`

```properties
# データベース設定（環境変数から取得）
spring.datasource.url=${DATABASE_URL}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}

# JPA設定
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# セキュリティ設定
server.error.include-message=never
server.error.include-stacktrace=never

# ログ設定
logging.level.root=INFO
logging.level.com.example.employee=INFO

# H2コンソール無効化
spring.h2.console.enabled=false
```

#### 2. `build.gradle` に本番依存関係を追加

```gradle
dependencies {
    // 既存の依存関係...
    
    // Production databases
    runtimeOnly 'org.postgresql:postgresql'
    // または MySQL
    // runtimeOnly 'mysql:mysql-connector-java'
}
```

---

## 🔒 セキュリティ設定

### 環境変数（本番環境）

以下の環境変数を設定してください：

```bash
# データベース
DATABASE_URL=jdbc:postgresql://host:5432/dbname
DB_USERNAME=your_username
DB_PASSWORD=your_password

# Spring Profile
SPRING_PROFILES_ACTIVE=prod

# ポート（プラットフォームによって自動設定）
PORT=8080
```

### application-prod.properties のセキュリティ強化

```properties
# セッション設定
server.servlet.session.timeout=30m
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.secure=true

# エラーページ設定
server.error.whitelabel.enabled=false
server.error.include-exception=false
server.error.include-message=never
server.error.include-stacktrace=never
```

---

## 🧪 デプロイ前のチェックリスト

- [ ] PostgreSQL依存関係を追加
- [ ] `application-prod.properties` を作成
- [ ] H2コンソールを無効化
- [ ] ログレベルをINFOに変更
- [ ] エラーメッセージの詳細を非表示
- [ ] データベース接続情報を環境変数化
- [ ] build.gradleを更新
- [ ] ローカルでテスト実行
- [ ] GitHubにプッシュ

---

## 🐛 トラブルシューティング

### データベース接続エラー

```
Failed to obtain JDBC Connection
```

**解決策**:
- DATABASE_URLが正しく設定されているか確認
- データベースサービスが起動しているか確認
- ファイアウォール設定を確認

### ポートエラー

```
Port 8080 is already in use
```

**解決策**:
- プラットフォームの環境変数 `$PORT` を使用
- Start Command: `java -Dserver.port=$PORT ...`

### ビルドエラー

```
Gradle build failed
```

**解決策**:
- Java 21が使用されているか確認
- `./gradlew clean build` を実行
- テストをスキップ: `./gradlew build -x test`

---

## 📊 推奨デプロイ先の比較

| プラットフォーム | 無料枠 | 難易度 | PostgreSQL | 自動デプロイ | おすすめ度 |
|------------|------|-------|-----------|----------|----------|
| **Render** | ✅ | ⭐⭐ | ✅ | ✅ | ⭐⭐⭐⭐⭐ |
| **Railway** | 💵 $5/月 | ⭐ | ✅ | ✅ | ⭐⭐⭐⭐⭐ |
| **Heroku** | ❌ | ⭐⭐ | ✅ | ✅ | ⭐⭐⭐ |
| **Docker** | 自分でホスト | ⭐⭐⭐⭐ | ✅ | ❌ | ⭐⭐⭐⭐ |

---

## 🔗 関連リンク

- **Repository**: https://github.com/skdhc592/employee-management
- **Render**: https://render.com
- **Railway**: https://railway.app
- **Heroku**: https://heroku.com
- **Docker**: https://docker.com

---

## 💡 次のステップ

1. デプロイプラットフォームを選択
2. 本番用設定ファイルを作成
3. PostgreSQL依存関係を追加
4. GitHubにプッシュ
5. デプロイ実行
6. カスタムドメインの設定（オプション）
7. 監視・ロギング設定（オプション）

---

**ご不明な点があれば、Issues でお気軽にご質問ください！**

https://github.com/skdhc592/employee-management/issues
