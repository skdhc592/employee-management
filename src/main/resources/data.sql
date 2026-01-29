-- 部署データの投入
INSERT INTO departments (department_name, created_at) VALUES ('営業部', CURRENT_TIMESTAMP);
INSERT INTO departments (department_name, created_at) VALUES ('開発部', CURRENT_TIMESTAMP);
INSERT INTO departments (department_name, created_at) VALUES ('人事部', CURRENT_TIMESTAMP);
INSERT INTO departments (department_name, created_at) VALUES ('総務部', CURRENT_TIMESTAMP);
INSERT INTO departments (department_name, created_at) VALUES ('経理部', CURRENT_TIMESTAMP);

-- 社員データの投入
-- 営業部
INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('山田', '太郎', 'yamada.taro@example.com', 1, '2020-04-01', 350000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('鈴木', '一郎', 'suzuki.ichiro@example.com', 1, '2019-07-15', 380000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 開発部
INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('佐藤', '花子', 'sato.hanako@example.com', 2, '2021-04-01', 400000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('田中', '次郎', 'tanaka.jiro@example.com', 2, '2020-10-01', 420000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('高橋', '三郎', 'takahashi.saburo@example.com', 2, '2018-04-01', 480000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 人事部
INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('伊藤', '美咲', 'ito.misaki@example.com', 3, '2022-04-01', 330000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('渡辺', '健太', 'watanabe.kenta@example.com', 3, '2021-07-01', 350000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 総務部
INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('中村', '由美', 'nakamura.yumi@example.com', 4, '2020-04-01', 340000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 経理部
INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('小林', '直樹', 'kobayashi.naoki@example.com', 5, '2019-04-01', 390000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('加藤', '真理', 'kato.mari@example.com', 5, '2023-04-01', 320000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 退職済み社員（テスト用）
INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('吉田', '太一', 'yoshida.taichi@example.com', 1, '2017-04-01', 360000, '退職済', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 追加データ（ページング確認用）
INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('木村', '優子', 'kimura.yuko@example.com', 3, '2022-07-01', 330000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('山本', '健', 'yamamoto.ken@example.com', 2, '2021-04-01', 410000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('斉藤', '美香', 'saito.mika@example.com', 4, '2023-04-01', 310000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('松本', '大輔', 'matsumoto.daisuke@example.com', 1, '2020-07-01', 370000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO employees (last_name, first_name, email, department_id, hire_date, salary, status, created_at, updated_at) 
VALUES ('井上', '愛', 'inoue.ai@example.com', 5, '2022-04-01', 340000, '在籍中', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);