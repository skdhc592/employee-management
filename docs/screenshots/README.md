# スクリーンショット / Screenshots

このディレクトリには、Employee Management Systemの画面キャプチャを配置します。

## 📸 必要なスクリーンショット

アプリケーションを起動して、以下の画面のスクリーンショットを撮影してください：

### 1. 社員一覧画面 (`list.png`)
- URL: `http://localhost:8080/employees`
- 説明: 社員一覧、検索機能、フィルター機能を表示

### 2. 社員詳細画面 (`detail.png`)
- URL: `http://localhost:8080/employees/{id}`
- 説明: 個別の社員情報の詳細表示

### 3. 社員登録フォーム (`form-new.png`)
- URL: `http://localhost:8080/employees/new`
- 説明: 新規社員登録フォーム

### 4. 社員編集フォーム (`form-edit.png`)
- URL: `http://localhost:8080/employees/{id}/edit`
- 説明: 社員情報編集フォーム

### 5. ダッシュボード (`dashboard.png`)
- URL: `http://localhost:8080/employees/dashboard`
- 説明: 部署別統計情報

## 📋 スクリーンショット撮影手順

1. アプリケーションを起動
   ```bash
   ./gradlew bootRun
   ```

2. ブラウザで各URLにアクセス

3. スクリーンショットを撮影
   - Windows: `Win + Shift + S`
   - macOS: `Cmd + Shift + 4`

4. このディレクトリに画像を保存

5. README.mdを更新してスクリーンショットを埋め込む

## 🖼️ 画像の命名規則

- `list.png` - 社員一覧
- `detail.png` - 社員詳細
- `form-new.png` - 新規登録フォーム
- `form-edit.png` - 編集フォーム
- `dashboard.png` - ダッシュボード
- `search.png` - 検索機能（オプション）
- `csv-export.png` - CSV出力（オプション）

## 📝 README.mdへの追加方法

スクリーンショットを撮影したら、プロジェクトルートの`README.md`の「スクリーンショット」セクションに以下のように追加してください：

```markdown
### 社員一覧画面 / Employee List
![社員一覧](docs/screenshots/list.png)

### 社員詳細画面 / Employee Detail
![社員詳細](docs/screenshots/detail.png)

### 社員登録フォーム / Employee Form
![社員登録](docs/screenshots/form-new.png)

### ダッシュボード / Dashboard
![ダッシュボード](docs/screenshots/dashboard.png)
```
