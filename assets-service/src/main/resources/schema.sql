DROP TABLE IF EXISTS assets;

CREATE TYPE asset_enum as ENUM ('Tangible', 'Intangible', 'Current', 'Fixed', 'Operating',
'NotOperating');

CREATE TABLE assets (
  asset_id        TEXT PRIMARY KEY NOT NULL,
  organization_id TEXT NOT NULL,
  asset_type      asset_enum NOT NULL,
  asset_name      TEXT NOT NULL,
  comment         VARCHAR(100));


INSERT INTO assets  (asset_id,  organization_id, asset_type, asset_name, comment)
VALUES ('c0cab9e7-2c71-4c12-af72-c6dbe98d0f77', '2f341f84-accd-43a6-abbc-70d41de2c9d3', 'Tangible','investments', 'excellent');
INSERT INTO assets  (asset_id,  organization_id, asset_type, asset_name, comment)
VALUES ('b060bf0e-3af8-4fcb-b72d-374f449833ad', 'ed8f80ce2-e247-4f7b-a8ed-472f279dd9d8', 'Intangible','inventory', 'good condition');
INSERT INTO assets  (asset_id,  organization_id, asset_type, asset_name, comment)
VALUES ('fc3c3e7a-ee2e-47fa-8532-ade4ad0d691c', '562f5ed0-b0bf-40cf-85c1-e560631057b8', 'Current','land', 'Very good');
INSERT INTO assets  (asset_id,  organization_id, asset_type, asset_name, comment)
VALUES ('b6f5f416-c583-4b6d-8386-dae4c3c966ae', '7d9bffc1-e80e-4d68-be7e-a039b9c78a53', 'FIxed','supplies', 'Fantastic');









