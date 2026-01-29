# 変更をGitHubにプッシュする手順

以下のコマンドをコマンドプロンプトまたはPowerShellで実行してください。

## 📝 追加されたファイル

### 1. LICENSEファイル ✅
- `LICENSE` - MITライセンス

### 2. GitHub Actions（CI/CD） ✅
- `.github/workflows/ci.yml` - 自動ビルド・テスト

### 3. Issueテンプレート ✅
- `.github/ISSUE_TEMPLATE/bug_report.md` - バグ報告用
- `.github/ISSUE_TEMPLATE/feature_request.md` - 機能要望用
- `.github/ISSUE_TEMPLATE/config.yml` - テンプレート設定
- `.github/PULL_REQUEST_TEMPLATE.md` - PR用テンプレート

### 4. ドキュメント ✅
- `docs/CONTRIBUTING.md` - 貢献ガイドライン
- `docs/screenshots/README.md` - スクリーンショット配置ガイド
- `docs/index.md` - GitHub Pages用トップページ
- `docs/_config.yml` - GitHub Pages設定

### 5. その他 ✅
- `.github/labels.yml` - 推奨ラベル設定
- `.github/FUNDING.yml` - スポンサー設定（オプション）
- `README.md` - 更新（バッジ追加、リンク追加）

## 🚀 プッシュコマンド

```bash
cd c:\Users\skdhc\projects\employee-management

git add .
git commit -m "Add: LICENSE, GitHub Actions, Issue templates, and documentation"
git push origin main
```

## 📋 プッシュ後の設定

### GitHub Pagesを有効化

1. GitHubリポジトリページにアクセス
2. `Settings` タブをクリック
3. 左メニューから `Pages` を選択
4. `Source` で `Deploy from a branch` を選択
5. `Branch` で `main` ブランチと `/docs` フォルダを選択
6. `Save` をクリック

数分後、以下のURLでドキュメントが公開されます：
https://skdhc592.github.io/employee-management/

### ラベルの設定（オプション）

`.github/labels.yml` に記載されたラベルを手動で設定：

1. リポジトリの `Issues` タブ
2. `Labels` をクリック
3. 推奨ラベルを追加

または、GitHub CLI を使用：
```bash
gh label create "priority: high" --color "d93f0b" --description "高優先度"
# 他のラベルも同様に作成
```

### GitHub Actionsの確認

プッシュ後、自動的にCI/CDパイプラインが実行されます：

1. リポジトリの `Actions` タブで確認
2. ビルドとテストが自動実行されます
3. バッジが更新されます

## ✅ 完了チェックリスト

- [ ] 変更をコミット・プッシュ
- [ ] GitHub Pagesを有効化
- [ ] Actionsタブでビルド成功を確認
- [ ] ラベルを設定（オプション）
- [ ] スクリーンショットを追加（後で）
