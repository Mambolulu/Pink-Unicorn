-- Role
INSERT INTO role (id, name) VALUES
                                ('3b405974-5553-4646-99ec-8119b9a16ad6', 'CLIENT'),
                                ('c3b1c9e9-5b7a-4b1a-9b7e-9c1a042474e0', 'ADMIN');

-- Rank
INSERT INTO rank (id, discount, name, needed_seeds) VALUES
                                ('ed5cc869-522c-4dec-94f8-3d16a4e6d159', 0.0, 'Bronze', 0),
                                ('494e556c-566c-416f-8e76-86f951b25469', 4.0, 'Silver', 20),
                                ('e6a2be4e-fe5e-4819-aebf-483b0bafc16e', 7.0, 'Gold', 60),
                                ('4e99deeb-4982-4f0a-86fe-0974c7273fac', 9.0, 'Platinum', 140),
                                ('726574fb-2f19-4996-8c1b-a0dbf2234910', 11.0, 'Diamond', 300);

-- Authority
INSERT INTO authority (id, name) VALUES
                                ('96828a82-4ddf-47a8-8e58-938d0a0c399e', 'CAN_PLACE_ORDER'),
                                ('8d67e034-5b60-4ae0-bfb3-69b1f90f3930', 'CAN_RETRIEVE_PURCHASE_HISTORY'),
                                ('22f9238f-e454-4196-a1a2-72be4e97d10c', 'CAN_RETRIEVE_PRODUCTS'),
                                ('a8091de1-77d8-42c7-a078-cfda4f5f6c7f', 'USER_MODIFY'),
                                ('d1bedaf4-3dc0-4e23-87d0-c1414382a3cf', 'USER_DELETE');

-- Role_Authority (CLIENT)
INSERT INTO role_authority (role_id, authority_id) VALUES
                                ('3b405974-5553-4646-99ec-8119b9a16ad6', '96828a82-4ddf-47a8-8e58-938d0a0c399e'), -- CAN_PLACE_ORDER
                                ('3b405974-5553-4646-99ec-8119b9a16ad6', '8d67e034-5b60-4ae0-bfb3-69b1f90f3930'), -- CAN_RETRIEVE_PURCHASE_HISTORY
                                ('3b405974-5553-4646-99ec-8119b9a16ad6', '22f9238f-e454-4196-a1a2-72be4e97d10c'); -- CAN_RETRIEVE_PRODUCTS

-- Role_Authority (ADMIN)
INSERT INTO role_authority (role_id, authority_id) VALUES
                                ('c3b1c9e9-5b7a-4b1a-9b7e-9c1a042474e0', '96828a82-4ddf-47a8-8e58-938d0a0c399e'), -- CAN_PLACE_ORDER
                                ('c3b1c9e9-5b7a-4b1a-9b7e-9c1a042474e0', '8d67e034-5b60-4ae0-bfb3-69b1f90f3930'), -- CAN_RETRIEVE_PURCHASE_HISTORY
                                ('c3b1c9e9-5b7a-4b1a-9b7e-9c1a042474e0', '22f9238f-e454-4196-a1a2-72be4e97d10c'), -- CAN_RETRIEVE_PRODUCTS
                                ('c3b1c9e9-5b7a-4b1a-9b7e-9c1a042474e0', 'a8091de1-77d8-42c7-a078-cfda4f5f6c7f'), -- USER_MODIFY
                                ('c3b1c9e9-5b7a-4b1a-9b7e-9c1a042474e0', 'd1bedaf4-3dc0-4e23-87d0-c1414382a3cf'); -- USER_DELETE

-- Canton
INSERT INTO canton (id, abbreviation, name) VALUES
                                ('8cf6b643-3869-4d16-bacb-c682718c6135', 'AG', 'Aargau'),
                                ('e1f5d2a5-e1e8-41ea-8889-633c53cb4793', 'AI', 'Appenzell Innerrhoden'),
                                ('59241a22-c9ce-4a49-aed6-83ec7492ff56', 'AR', 'Appenzell Ausserrhoden'),
                                ('91ed3e29-793b-4404-99c7-7d9b771d427f', 'BE', 'Bern'),
                                ('9688e930-29fa-4846-bdf8-23d7f038f7d7', 'BL', 'Basel-Landschaft'),
                                ('1225328a-6a50-43c5-8d56-feee027fc738', 'BS', 'Basel-Stadt'),
                                ('2c18367f-9a76-4f91-86b3-3cb501fcd35b', 'FR', 'Freiburg'),
                                ('5c3947d4-d0dc-4fc0-bc4c-c21ac53d6893', 'GE', 'Genf'),
                                ('5c7fb82a-b2b1-4db7-89a0-c00488292b19', 'GL', 'Glarus'),
                                ('ccd4a9e6-bef1-47ec-abfd-170538988f58', 'GR', 'Graubünden'),
                                ('76fd010b-968c-4255-b632-80f0b1270a2c', 'JU', 'Jura'),
                                ('27de9ce9-a3d9-4ea0-b4a7-6bf4f8f465d4', 'LU', 'Luzern'),
                                ('9bf8c41a-cd87-48bc-bcc4-fd14c504e7dd', 'NE', 'Neuenburg'),
                                ('eed7a71e-bf7b-4f57-9a03-aee0594b0509', 'NW', 'Nidwalden'),
                                ('55cb0b18-0011-4c85-8ecc-ad3500dad398', 'OW', 'Obwalden'),
                                ('c2fefc0f-069d-4d3d-bd41-81654b87fcf9', 'SG', 'St. Gallen'),
                                ('843ad085-68d4-4234-a248-c447dc67d9c9', 'SH', 'Schaffhausen'),
                                ('b475342f-2e23-44e3-a8d0-c899b584ecb7', 'SO', 'Solothurn'),
                                ('a40bbb6d-13b3-4154-8256-bc8c5e6779c1', 'SZ', 'Schwyz'),
                                ('fb5ef9e4-584e-4f88-8e9a-4dacfd52482d', 'TG', 'Thurgau'),
                                ('6e1c5239-539d-4299-bf4b-cffaf2e30f42', 'TI', 'Tessin'),
                                ('de083fad-ba70-474f-a93f-79260d64bc57', 'UR', 'Uri'),
                                ('9a3c05a0-77eb-4365-af8a-b91ced878d79', 'VD', 'Waadt'),
                                ('f6c6fe2d-82c6-4a37-a725-507cd0f7ad50', 'VS', 'Wallis'),
                                ('79c3df07-8f5c-4297-bb56-0924609fd3e7', 'ZG', 'Zug'),
                                ('c47e8614-d500-486f-9561-08d3f1e93359', 'ZH', 'Zürich');

-- Place
INSERT INTO place (id, name, canton) VALUES
                                ('578e5ab0-de4d-487d-a209-a143f255714d', 'Dänikon ZH', 'c47e8614-d500-486f-9561-08d3f1e93359'), -- Zürich
                                ('f1560c3f-693f-4803-b2c7-4a0e177fcf99', 'Madulain', 'ccd4a9e6-bef1-47ec-abfd-170538988f58'), -- Graubünden
                                ('e756ca11-225e-422d-95ca-233ba80fcfd7', 'Aranno', '6e1c5239-539d-4299-bf4b-cffaf2e30f42'); -- Tessin


-- ZipCode
INSERT INTO zip_code (id, zip_code, place) VALUES
                                ('ce5ce0f6-724d-4028-8aa3-844b4e5afa7c', '8114', '578e5ab0-de4d-487d-a209-a143f255714d'), -- Dänikon ZH
                                ('c5e567ba-401d-46bf-881d-a22851bd6e23', '7523', 'f1560c3f-693f-4803-b2c7-4a0e177fcf99'), -- Madulain
                                ('7aead896-2bac-47a7-90e8-f4ed379dc117', '6994', 'e756ca11-225e-422d-95ca-233ba80fcfd7'); -- Aranno

-- Origin
INSERT INTO origins (id, country) VALUES
                                 ('ed7399cb-8b98-4e25-9b5c-7e8b2bf2e06d', 'Taiwan'),
                                 ('e41b92fe-81c1-4a37-9b23-71d86ee2f0f8', 'China'),
                                 ('2ea3b670-90e2-4421-b067-3f7f6c19b9b2', 'Japan');

-- Category
INSERT INTO categories (id, name) VALUES
                                 ('d8a0f93b-7a76-45bd-b9ed-4e1cf44df36a', 'White'),
                                 ('e41b92fe-81c1-4a37-9b23-71d86ee2f0f8', 'Green'),
                                 ('2ea3b670-90e2-4421-b067-3f7f6c19b9b2', 'Oolong'),
                                 ('3c8e0889-d715-47ce-9076-99614ec5c106', 'Black'),
                                 ('ef3c631c-0d1a-40d3-9e9b-c09ea7edf25e', 'Medicinal Herbs');

-- Product
INSERT INTO products (id, variety, category_id, origin_id, purchase_price_per100g, selling_price_per100g, harvest_date, stock) VALUES
                                -- White
                                ('fba7c58d-d18d-4ef1-be6e-5a0a4b99d8ad', 'Heirloom Silver Needle',  'd8a0f93b-7a76-45bd-b9ed-4e1cf44df36a', 'ed7399cb-8b98-4e25-9b5c-7e8b2bf2e06d', 83, 112, '2022-11-04', 100),
                                ('49441ce4-e668-465d-8f30-0682ee9349b3', 'Floating Lotus',          'd8a0f93b-7a76-45bd-b9ed-4e1cf44df36a', '2ea3b670-90e2-4421-b067-3f7f6c19b9b2', 78, 104, '2022-10-12', 15),
                                -- Green
                                ('ce0f2ddc-66de-4338-9125-f02c3b881680', 'Fur Peak',                'e41b92fe-81c1-4a37-9b23-71d86ee2f0f8', 'e41b92fe-81c1-4a37-9b23-71d86ee2f0f8', 56, 89,  '2021-01-06', 56),
                                ('7c301b32-e467-458f-9fee-36fd4efcb163', 'Melon Seed Green',        'e41b92fe-81c1-4a37-9b23-71d86ee2f0f8', '2ea3b670-90e2-4421-b067-3f7f6c19b9b2', 87, 142, '2022-05-18', 54),
                                -- Oolong
                                ('0217d03c-bf19-4ad0-94f9-f76511cd8c67', 'Duck Sh*t Oolong',        '2ea3b670-90e2-4421-b067-3f7f6c19b9b2', 'e41b92fe-81c1-4a37-9b23-71d86ee2f0f8', 69, 89,  '2021-07-05', 43),
                                ('a4d9d49e-9707-45b3-bd52-f67d5408ac5a', 'Summer Haze',             '2ea3b670-90e2-4421-b067-3f7f6c19b9b2', '2ea3b670-90e2-4421-b067-3f7f6c19b9b2', 16, 53,  '2020-10-07', 61),
                                -- Black
                                ('0ec0e9e2-e321-44bb-8f6c-d64ba323ee4f', 'Push Button Smile',       '3c8e0889-d715-47ce-9076-99614ec5c106', 'e41b92fe-81c1-4a37-9b23-71d86ee2f0f8', 77, 120, '2021-01-18', 70),
                                ('700af9a2-0cd9-4b72-babf-1cceb8215cb8', 'Cocoa Malt Queen',        '3c8e0889-d715-47ce-9076-99614ec5c106', 'ed7399cb-8b98-4e25-9b5c-7e8b2bf2e06d', 36, 54,  '2020-03-23', 55),
                                -- Medical Herb
                                ('972b7005-201f-4e25-96c1-c535424b9abc', 'Angelica Pack',           'ef3c631c-0d1a-40d3-9e9b-c09ea7edf25e', 'ed7399cb-8b98-4e25-9b5c-7e8b2bf2e06d', 42, 69,  '2021-09-29', 33),
                                ('1fd0f331-aec4-4f3c-9917-329c06738bc9', 'Senna Leaves',            'ef3c631c-0d1a-40d3-9e9b-c09ea7edf25e', 'e41b92fe-81c1-4a37-9b23-71d86ee2f0f8', 52, 79,  '2020-11-15', 12);

-- User
INSERT INTO users (id, created_at, modified_at, address, birth_date, email, first_name, is_active, last_name, password, seeds, created_by, last_modified_by, rank, zip_code) VALUES
                                -- Admin
                                ('47143259-e965-450a-8244-aade6fd462c5', '2023-12-23 11:48:48.165161', '2023-12-23 11:48:48.165161', 'Playboystrasse 420', '1999-12-12', 'admin@admin.ch', 'Hengst', true, 'Stecher', '$2a$10$x/YapnuB5id.dy4ESkXAse7FJTfltIt7s.LqqdXngBz/vrWdPBaxq', 9000, null, null, '726574fb-2f19-4996-8c1b-a0dbf2234910', 'ce5ce0f6-724d-4028-8aa3-844b4e5afa7c'),
                                -- Client
                                ('d822f2d2-61b5-495b-8618-5beb63a8b743', '2023-12-23 11:48:48.165161', '2023-12-23 11:48:48.165161', 'TheOnePiece is Real', '1999-12-12', 'client@client.ch', 'Gol D', true, 'Roger', '$2a$10$BXvY9aYKLNCnLcyXuYDY..1uJzHOmJH5Qo1G48DE5vWvripCMh7xq', 0, null, null, 'ed5cc869-522c-4dec-94f8-3d16a4e6d159', 'ce5ce0f6-724d-4028-8aa3-844b4e5afa7c');

-- User_Role
INSERT INTO users_role (users_id, role_id) VALUES
                                -- Admin
                                ('47143259-e965-450a-8244-aade6fd462c5', 'c3b1c9e9-5b7a-4b1a-9b7e-9c1a042474e0'),
                                -- Client
                                ('d822f2d2-61b5-495b-8618-5beb63a8b743', '3b405974-5553-4646-99ec-8119b9a16ad6');