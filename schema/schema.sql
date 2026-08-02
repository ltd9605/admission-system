CREATE TABLE "users" (
  "id" varchar PRIMARY KEY,
  "user_name" varchar,
  "password" varchar,
  "full_name" varchar,
  "email" varchar,
  "role_ID" varchar,
  "created_at" timestamp,
  "is_active" boolean
);

CREATE TABLE "roles" (
  "id" varchar PRIMARY KEY,
  "role_name" varchar
);

CREATE TABLE "permissions" (
  "id" varchar PRIMARY KEY,
  "code" varchar UNIQUE,
  "permission_name" varchar
);

CREATE TABLE "role_permissions" (
  "role_id" varchar,
  "permission_id" varchar,
  PRIMARY KEY ("role_id", "permission_id")
);

CREATE TABLE "candidates" (
  "id" varchar PRIMARY KEY,
  "identitycard" varchar UNIQUE,
  "register_number" varchar,
  "fullname" varchar,
  "birthday" timestamp,
  "password" varchar,
  "phone_number" varchar,
  "gender" varchar(50),
  "enrollment_year" integer,
  "priority_point" float,
  "folks" varchar,
  "birthplace" varchar,
  "priority_area" varchar,
  "priority_target" varchar,
  "status" varchar(50)
);

CREATE TABLE "majors" (
  "id" varchar PRIMARY KEY,
  "code" varchar UNIQUE,
  "name" varchar,
  "quota" integer,
  "minimum_score" float,
  "admission_score" float,
  "is_closed" boolean
);

CREATE TABLE "subjects" (
  "id" varchar PRIMARY KEY,
  "code" varchar UNIQUE,
  "name" varchar
);

CREATE TABLE "combinations" (
  "id" varchar PRIMARY KEY,
  "code" varchar UNIQUE,
  "name" varchar
);

CREATE TABLE "subject_combinations" (
  "combination_code" varchar,
  "subject_code" varchar,
  PRIMARY KEY(combination_code,subject_code)
);

CREATE TABLE "major_combinations" (
  "id" varchar PRIMARY KEY,
  "major_code" varchar,
  "combination_code" varchar,
  "deviation" float
);

CREATE TABLE "major_combination_subject" (
  "id" varchar PRIMARY KEY,
  "major_combinations_id" varchar,
  "subject_code" varchar,
  "weight" float
);

CREATE TABLE "scores" (
  "id" varchar PRIMARY KEY,
  "candidate_identitycard" varchar,
  "subject_code" varchar,
  "score" float,
  "exam_type" varchar
);

CREATE TABLE "bonus_scores" (
  "id" varchar PRIMARY KEY,
  "candidate_identitycard" varchar,
  "type" varchar,
  "score" float,
  "convert_point" float
);

CREATE TABLE "aspirations" (
  "id" varchar PRIMARY KEY,
  "candidate_identitycard" varchar,
  "priority_number" integer,
  "major_code" varchar,
  "combination_code" varchar,
  "calculated_admission_score" float,
  "admission_method" varchar,
  "status" varchar(50)
);

CREATE TABLE "conversion_rules" (
  "id" varchar PRIMARY KEY,
  "method" varchar,
  "convert_subject_code" varchar,
  "convert_combination_code" varchar,
  "score_a" float,
  "score_b" float,
  "converted_score_c" float,
  "converted_score_d" float,
  "percentile" varchar
);

CREATE TABLE "certificates" (
  "id" varchar PRIMARY KEY,
  "candidate_identitycard" varchar,
  "insuer" varchar,
  "code" varchar,
  "certificate_name" varchar
);

CREATE UNIQUE INDEX "unique_major_combo_subject" ON "major_combination_subject" ("major_combinations_id", "subject_code");

CREATE UNIQUE INDEX "unique_candidate_score" ON "scores" ("candidate_identitycard", "subject_code", "exam_type");

ALTER TABLE "users" ADD FOREIGN KEY ("role_ID") REFERENCES "roles" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "role_permissions" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "role_permissions" ADD FOREIGN KEY ("permission_id") REFERENCES "permissions" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "subject_combinations" ADD FOREIGN KEY ("combination_code") REFERENCES "combinations" ("code") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "subject_combinations" ADD FOREIGN KEY ("subject_code") REFERENCES "subjects" ("code") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "major_combinations" ADD FOREIGN KEY ("major_code") REFERENCES "majors" ("code") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "major_combinations" ADD FOREIGN KEY ("combination_code") REFERENCES "combinations" ("code") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "aspirations" ADD FOREIGN KEY ("candidate_identitycard") REFERENCES "candidates" ("identitycard") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "aspirations" ADD FOREIGN KEY ("major_code") REFERENCES "majors" ("code") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "aspirations" ADD FOREIGN KEY ("combination_code") REFERENCES "combinations" ("code") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "scores" ADD FOREIGN KEY ("subject_code") REFERENCES "subjects" ("code") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "scores" ADD FOREIGN KEY ("candidate_identitycard") REFERENCES "candidates" ("identitycard") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "bonus_scores" ADD FOREIGN KEY ("candidate_identitycard") REFERENCES "candidates" ("identitycard") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "conversion_rules" ADD FOREIGN KEY ("convert_subject_code") REFERENCES "subjects" ("code") DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "conversion_rules" ADD FOREIGN KEY ("convert_combination_code") REFERENCES "combinations" ("code") DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "certificates" ADD FOREIGN KEY ("candidate_identitycard") REFERENCES "candidates" ("identitycard") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "major_combination_subject" ADD FOREIGN KEY ("major_combinations_id") REFERENCES "major_combinations" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "major_combination_subject" ADD FOREIGN KEY ("subject_code") REFERENCES "subjects" ("code") DEFERRABLE INITIALLY IMMEDIATE;
