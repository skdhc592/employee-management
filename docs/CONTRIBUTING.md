# 貢献ガイドライン / Contributing Guidelines

**Repository**: https://github.com/skdhc592/employee-management

Employee Management Systemへの貢献に興味を持っていただき、ありがとうございます！

このドキュメントでは、プロジェクトへの貢献方法について説明します。

## 🤝 貢献の方法 / How to Contribute

### 1. Issueを作成する

バグ報告や機能要望は、まずIssueを作成してください。

- 🐛 [バグ報告](https://github.com/skdhc592/employee-management/issues/new?template=bug_report.md)
- ✨ [機能要望](https://github.com/skdhc592/employee-management/issues/new?template=feature_request.md)

### 2. フォークとクローン

```bash
# リポジトリをフォーク（GitHubのWebインターフェースから）
# https://github.com/skdhc592/employee-management

# フォークしたリポジトリをクローン
git clone https://github.com/YOUR_USERNAME/employee-management.git
cd employee-management

# オリジナルリポジトリをupstreamとして追加
git remote add upstream https://github.com/skdhc592/employee-management.git
```

### 3. ブランチを作成

```bash
# 機能追加の場合
git checkout -b feature/your-feature-name

# バグ修正の場合
git checkout -b fix/bug-description
```

### 4. 変更を実装

- コードスタイルガイドに従ってください
- 適切なコメントを追加してください
- テストを追加/更新してください

### 5. テストを実行

```bash
./gradlew test
./gradlew build
```

### 6. コミット

コミットメッセージは明確で説明的にしてください。

```bash
git add .
git commit -m "Add: 新機能の説明"
```

#### コミットメッセージの規則

- `Add:` - 新機能追加
- `Fix:` - バグ修正
- `Update:` - 既存機能の更新
- `Remove:` - 機能削除
- `Refactor:` - リファクタリング
- `Docs:` - ドキュメント更新
- `Test:` - テスト追加/修正
- `Style:` - コードスタイル変更

### 7. プッシュとPull Request

```bash
git push origin feature/your-feature-name
```

GitHubでPull Requestを作成してください。

## 📋 コーディング規約 / Coding Standards

### Java

- Java 21の機能を活用してください
- Lombokアノテーションを適切に使用してください
- パッケージ構成を守ってください
  - `controller` - コントローラー層
  - `service` - サービス層
  - `repository` - リポジトリ層
  - `entity` - エンティティ
  - `dto` - データ転送オブジェクト

### コードスタイル

- インデント: スペース4つ
- 変数名: camelCase
- クラス名: PascalCase
- 定数: UPPER_SNAKE_CASE
- 1行の長さ: 120文字以内

### コメント

```java
/**
 * クラスやメソッドの説明
 * 
 * @param パラメータ名 パラメータの説明
 * @return 戻り値の説明
 */
```

## 🧪 テスト / Testing

- すべての新機能にテストを追加してください
- 既存のテストが通過することを確認してください
- テストカバレッジを維持してください

```bash
# すべてのテストを実行
./gradlew test

# 特定のテストを実行
./gradlew test --tests com.example.employee.service.EmployeeServiceImplTest
```

## 📝 ドキュメント / Documentation

コードの変更に伴い、以下のドキュメントも更新してください：

- README.md
- JavaDoc
- application.properties のコメント

## 🔍 Pull Requestのレビュー基準

Pull Requestは以下の基準で評価されます：

- [ ] コードは動作し、期待通りの結果を返す
- [ ] テストが追加され、すべて通過している
- [ ] コーディング規約に従っている
- [ ] 適切なコメントが追加されている
- [ ] ドキュメントが更新されている
- [ ] コミットメッセージが明確である
- [ ] 既存の機能を壊していない

## 🏷️ ラベルの使用 / Labels

Issueには適切なラベルを付けてください：

- `bug` - バグ報告
- `enhancement` - 機能追加
- `documentation` - ドキュメント関連
- `good first issue` - 初心者向け
- `help wanted` - ヘルプ募集
- `question` - 質問
- `wontfix` - 対応しない
- `duplicate` - 重複

## ❓ 質問 / Questions

質問がある場合は：

1. [GitHub Discussions](https://github.com/skdhc592/employee-management/discussions)で質問
2. [Issue](https://github.com/skdhc592/employee-management/issues)を作成

## 📜 ライセンス / License

このプロジェクトに貢献することで、あなたの貢献がMITライセンスの下で公開されることに同意したものとみなされます。

## 🙏 謝辞 / Acknowledgments

あなたの貢献に感謝します！すべての貢献者は、プロジェクトの成功に不可欠です。

---

Happy Coding! 🚀
