INSERT INTO medicines (id,name,stock) VALUES (1,'Thuoc hiem',1) ON CONFLICT (id) DO NOTHING;
