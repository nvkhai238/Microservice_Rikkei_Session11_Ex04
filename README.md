# Session 11 - Bai 4: Redis distributed lock

Chay `docker compose up -d --wait` va `./gradlew :pharmacy-service:bootRun`.

Thuoc ID=1 co 1 hop. `POST /api/medicines/1/sell` voi JSON `{"quantity":1}`. Gui 2 request dong thoi:

```bash
seq 1 2 | xargs -P 2 -I{} curl -s -w ' HTTP:%{http_code}\n' -X POST http://localhost:8090/api/medicines/1/sell -H 'Content-Type: application/json' -d '{"quantity":1}'
```

Mot request thanh cong; request kia nhan HTTP 409 voi thong bao het hang. Lock co wait 3 giay, lease 5 giay. Giao dich DB commit truoc khi giai phong lock.
