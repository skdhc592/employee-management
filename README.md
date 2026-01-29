# Employee Management System（社員管理システム）

[![Java CI with Gradle](https://github.com/skdhc592/employee-management/actions/workflows/ci.yml/badge.svg)](https://github.com/skdhc592/employee-management/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.10-brightgreen.svg)](https://spring.io/projects/spring-boot)

**🔗 Repository**: https://github.com/skdhc592/employee-management

**📖 Documentation**: https://skdhc592.github.io/employee-management/

---

Spring Boot で構築された社員管理システムです。社員情報の登録・編集・削除、検索、CSV出力、部署別統計などの機能を提供します。

A comprehensive employee management system built with Spring Boot, featuring employee CRUD operations, search functionality, CSV export, and department statistics.

## 🚀 主な機能 / Key Features

- ✅ **社員情報管理** / Employee CRUD Operations
  - 社員の登録・編集・削除 / Create, Read, Update, Delete employees
  - 詳細情報の表示 / View detailed employee information
  
- 🔍 **検索機能** / Advanced Search
  - 氏名による検索 / Search by name
  - 部署による絞り込み / Filter by department
  - 在籍中の社員のみ表示 / Filter active employees only
  
- 📊 **ダッシュボード** / Dashboard
  - 部署別統計（人数、平均給与） / Department statistics (count, average salary)
  - 視覚的なデータ表示 / Visual data representation
  
- 📄 **CSV出力** / CSV Export
  - 社員一覧のCSVダウンロード / Export employee list to CSV
  - Excel対応（BOM付きUTF-8） / Excel-compatible format
  
- 🎨 **モダンなUI** / Modern UI
  - レスポンシブデザイン / Responsive design
  - Bootstrap 5ベース / Built with Bootstrap 5
  
- ✔️ **バリデーション** / Data Validation
  - 入力データの検証 / Input validation
  - メールアドレスの重複チェック / Email uniqueness validation
  
- 📖 **ページネーション** / Pagination
  - 大量データに対応 / Handle large datasets efficiently
  - ソート機能 / Sortable columns

## 🛠️ 技術スタック / Tech Stack

- **Backend**
  - Java 21
  - Spring Boot 3.5.10
  - Spring Data JPA
  - Spring Validation
  - Lombok

- **Frontend**
  - Thymeleaf
  - Bootstrap 5
  - HTML5 / CSS3

- **Database**
  - H2 Database (In-memory)

- **Build Tool**
  - Gradle 8.x

## 📋 前提条件 / Prerequisites

- Java 21 以上 / Java 21 or higher
- Gradle 8.x 以上（またはGradleラッパーを使用） / Gradle 8.x or higher (or use Gradle wrapper)

## 🔧 セットアップ / Setup

### 1. リポジトリのクローン / Clone the Repository

```bash
git clone https://github.com/skdhc592/employee-management.git
cd employee-management
```

### 2. アプリケーションの起動 / Run the Application

#### Windows
```bash
gradlew.bat bootRun
```

#### macOS / Linux
```bash
./gradlew bootRun
```

### 3. アプリケーションへのアクセス / Access the Application

アプリケーションが起動したら、ブラウザで以下にアクセスしてください。

Once the application is running, open your browser and navigate to:

```
http://localhost:8080/employees
```

### 4. H2 データベースコンソール（開発用） / H2 Database Console (Development)

データベースの内容を確認する場合は以下にアクセスしてください。

To view database contents, access:

```
http://localhost:8080/h2-console
```

**接続情報 / Connection Details:**
- JDBC URL: `jdbc:h2:mem:employeedb`
- Username: `sa`
- Password: (空白 / empty)

## 📁 プロジェクト構成 / Project Structure

```
employee-management/
├── src/
│   ├── main/
│   │   ├── java/com/example/employee/
│   │   │   ├── controller/          # コントローラー層
│   │   │   │   └── EmployeeController.java
│   │   │   ├── dto/                 # データ転送オブジェクト
│   │   │   │   ├── EmployeeForm.java
│   │   │   │   └── DepartmentStats.java
│   │   │   ├── entity/              # エンティティ層
│   │   │   │   ├── Employee.java
│   │   │   │   └── Department.java
│   │   │   ├── repository/          # リポジトリ層
│   │   │   │   ├── EmployeeRepository.java
│   │   │   │   └── DepartmentRepository.java
│   │   │   ├── service/             # サービス層
│   │   │   │   ├── EmployeeService.java
│   │   │   │   └── EmployeeServiceImpl.java
│   │   │   ├── validation/          # カスタムバリデーション
│   │   │   │   ├── PastOrPresent.java
│   │   │   │   └── PastOrPresentValidator.java
│   │   │   └── EmployeeManagementApplication.java
│   │   └── resources/
│   │       ├── templates/employees/ # Thymeleafテンプレート
│   │       │   ├── list.html       # 社員一覧
│   │       │   ├── detail.html     # 社員詳細
│   │       │   ├── form.html       # 登録・編集フォーム
│   │       │   └── dashboard.html  # ダッシュボード
│   │       ├── application.properties # アプリケーション設定
│   │       └── data.sql            # 初期データ
│   └── test/                        # テストコード
├── build.gradle                     # ビルド設定
├── settings.gradle
├── .gitignore
└── README.md
```

## 📸 スクリーンショット / Screenshots

アプリケーションを起動してスクリーンショットを撮影してください。詳細は [docs/screenshots/README.md](docs/screenshots/README.md) を参照してください。

Please run the application and take screenshots. See [docs/screenshots/README.md](docs/screenshots/README.md) for details.

### 社員一覧画面 / Employee List
社員の一覧表示、検索、フィルタリング機能を提供します。
Displays employee list with search and filter capabilities.

<!-- ![社員一覧](docs/screenshots/list.png) -->

### 社員詳細画面 / Employee Detail
個別の社員情報を詳細に表示します。
Shows detailed information for individual employees.

<!-- ![社員詳細](docs/screenshots/detail.png) -->

### 社員登録・編集フォーム / Employee Form
社員情報の登録・編集を行います。
Form for creating and editing employee information.

<!-- ![社員登録フォーム](docs/screenshots/form-new.png) -->

### ダッシュボード / Dashboard
部署別の統計情報を表示します。
Displays department-wise statistics.

<!-- ![ダッシュボード](docs/screenshots/dashboard.png) -->

## 🎯 使用方法 / Usage

### 社員の登録 / Creating an Employee
1. 「新規登録」ボタンをクリック / Click "新規登録" button
2. 必要な情報を入力 / Fill in required information
3. 「登録」ボタンをクリック / Click "登録" button

### 社員情報の編集 / Editing an Employee
1. 社員一覧から「編集」ボタンをクリック / Click "編集" button from employee list
2. 情報を編集 / Edit information
3. 「更新」ボタンをクリック / Click "更新" button

### 社員の削除 / Deleting an Employee
1. 社員詳細画面から「削除」ボタンをクリック / Click "削除" button from employee detail page
2. 確認ダイアログで「OK」をクリック / Confirm deletion

### 社員の検索 / Searching Employees
- 氏名で検索: 検索ボックスに氏名を入力 / Search by name: Enter name in search box
- 部署で絞り込み: 部署を選択 / Filter by department: Select department
- 在籍中のみ表示: チェックボックスをON / Show active only: Check the box

### CSV出力 / CSV Export
1. 社員一覧画面で「CSV出力」ボタンをクリック / Click "CSV出力" button on employee list page
2. ファイルがダウンロードされます / File will be downloaded

## 🔨 ビルド / Build

### JARファイルの作成 / Building JAR

```bash
./gradlew build
```

ビルドされたJARファイルは `build/libs/` ディレクトリに生成されます。

The built JAR file will be in the `build/libs/` directory.

### アプリケーションの実行 / Running the Application

```bash
java -jar build/libs/employee-management-0.0.1-SNAPSHOT.jar
```

## 🧪 テスト / Testing

```bash
./gradlew test
```

## 📝 データベーススキーマ / Database Schema

### Employee（社員）テーブル

| カラム名 / Column | 型 / Type | 説明 / Description |
|------------------|-----------|-------------------|
| employee_id | BIGINT | 社員ID（主キー） / Employee ID (Primary Key) |
| last_name | VARCHAR(50) | 姓 / Last Name |
| first_name | VARCHAR(50) | 名 / First Name |
| email | VARCHAR(100) | メールアドレス（ユニーク） / Email (Unique) |
| department_id | BIGINT | 部署ID（外部キー） / Department ID (Foreign Key) |
| hire_date | DATE | 入社日 / Hire Date |
| salary | DECIMAL | 給与 / Salary |
| status | VARCHAR(20) | 在籍状況 / Employment Status |
| created_at | TIMESTAMP | 作成日時 / Created At |
| updated_at | TIMESTAMP | 更新日時 / Updated At |

### Department（部署）テーブル

| カラム名 / Column | 型 / Type | 説明 / Description |
|------------------|-----------|-------------------|
| department_id | BIGINT | 部署ID（主キー） / Department ID (Primary Key) |
| department_name | VARCHAR(100) | 部署名（ユニーク） / Department Name (Unique) |
| created_at | TIMESTAMP | 作成日時 / Created At |

## 🌟 初期データ / Sample Data

アプリケーション起動時に以下の初期データが自動的に登録されます。

The following sample data is automatically loaded on application startup:

- **部署 / Departments**: 営業部、開発部、人事部、総務部、経理部
- **社員 / Employees**: 15名のサンプル社員データ

## ⚙️ 設定 / Configuration

主な設定は `src/main/resources/application.properties` で行えます。

Main configuration can be found in `src/main/resources/application.properties`:

- **ポート番号 / Server Port**: デフォルト 8080
- **データベース / Database**: H2 in-memory (自動で初期化)
- **ログレベル / Logging**: DEBUGレベルで詳細ログを出力

## 🤝 貢献 / Contributing

プルリクエストを歓迎します！大きな変更の場合は、まずissueを開いて変更内容を議論してください。

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

詳細な貢献ガイドラインは [CONTRIBUTING.md](docs/CONTRIBUTING.md) をご覧ください。

Please see [CONTRIBUTING.md](docs/CONTRIBUTING.md) for detailed contribution guidelines.

### クイックスタート / Quick Start

1. このリポジトリをフォーク / Fork the repository
2. フィーチャーブランチを作成 / Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. 変更をコミット / Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. ブランチにプッシュ / Push to the branch (`git push origin feature/AmazingFeature`)
5. プルリクエストを開く / Open a Pull Request

### Issueテンプレート / Issue Templates

- 🐛 [バグ報告 / Bug Report](.github/ISSUE_TEMPLATE/bug_report.md)
- ✨ [機能要望 / Feature Request](.github/ISSUE_TEMPLATE/feature_request.md)

## 📄 ライセンス / License

このプロジェクトはMITライセンスの下で公開されています。詳細は [LICENSE](LICENSE) ファイルをご覧ください。

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## 📧 お問い合わせ / Contact

プロジェクトに関する質問や提案がある場合は、GitHubのIssuesをご利用ください。

For questions or suggestions, please use GitHub Issues.

**GitHub Issues**: https://github.com/skdhc592/employee-management/issues

**Discussions**: https://github.com/skdhc592/employee-management/discussions

## 🙏 謝辞 / Acknowledgments

- Spring Boot
- Thymeleaf
- Bootstrap
- H2 Database
- Lombok

## 🔗 関連リンク / Related Links

- **GitHub Repository**: https://github.com/skdhc592/employee-management
- **Documentation**: https://skdhc592.github.io/employee-management/
- **Issues**: https://github.com/skdhc592/employee-management/issues
- **Pull Requests**: https://github.com/skdhc592/employee-management/pulls
- **Actions (CI/CD)**: https://github.com/skdhc592/employee-management/actions

---

⭐ このプロジェクトが役に立った場合は、ぜひスターをお願いします！

If you find this project useful, please consider giving it a star! ⭐

**Star this repository**: https://github.com/skdhc592/employee-management
