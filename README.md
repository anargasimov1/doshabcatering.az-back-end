# 🥗 doshabcatering.az – Back-End API

Bu repozitoriya [doshabcatering.az](https://doshabcatering.az) platformasının server hissəsini əhatə edir. Sistem REST API-lər vasitəsilə istifadəçi qeydiyyatı, məhsul idarəsi və sifarişlərin icrası kimi əsas funksiyaları təmin edir. Tətbiq Swagger sənədləşdirilməsi ilə təmin olunmuşdur və rahat test imkanı verir.

---

## 🚀 Texnologiyalar və Alətlər

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security + JWT**
- **REST API**
- **Docker & Docker Compose**
- **Gradle**
- **PostgreSQL**
- **Swagger (Springfox/Swagger UI)**
- **Lombok**

---

## 🗂 Layihə Strukturu

```plaintext
📦 doshabcatering.az-back-end
 ┣ 📁 src
 ┃ ┣ 📁 main
 ┃ ┃ ┣ 📁 java/...
 ┃ ┃ ┣ 📁 resources
 ┃ ┃ ┃ ┗ 📄 application.yaml
 ┣ 📁 uploads/images
 ┣ 📄 Dockerfile
 ┣ 📄 docker-compose.yml
 ┣ 📄 build.gradle
 ┗ 📄 README.md
```

---

## ⚙️ Qurulum

### Tələblər:

- Java 17+
- Docker və Docker Compose
- Git

### Addımlar:

1. Repozitoriyanı klonlayın:

```bash
git clone https://github.com/anargasimov1/doshabcatering.az-back-end.git
cd doshabcatering.az-back-end
```

2. Docker ilə tətbiqi başladın:

```bash
docker-compose up --build
```

3. Əgər Docker istifadə etmirsinizsə:

```bash
./gradlew bootRun
```

---

## 🌐 API və Swagger UI

Tətbiq uğurla işə salındıqdan sonra aşağıdakı link vasitəsilə Swagger interfeysini aça bilərsiniz:

📄 **Swagger UI**:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

**Əsas xüsusiyyətlər:**

- Endpoint-ləri birbaşa brauzerdə test edin
- JWT token əlavə etmək üçün auth bölməsi mövcuddur
- Ətraflı məlumatlar və cavab strukturları

> **Qeyd:** JWT ilə qorunan endpoint-lər üçün Swagger-də `Authorize` düyməsi vasitəsilə token əlavə etməlisiniz.

---

## 🛠️ API Nümunələri

| Metod | URL | Təsvir |
|-------|-----|--------|
| `POST` | `/api/auth/login` | İstifadəçi girişi |
| `POST` | `/api/auth/register` | Yeni istifadəçi qeydiyyatı |
| `GET`  | `/api/products` | Məhsulların siyahısı |
| `POST` | `/api/order` | Yeni sifariş |

---

## 📤 Media Fayllar

Yüklənmiş şəkillər `uploads/images/` qovluğunda saxlanılır. API bu faylları HTTP vasitəsilə təqdim edir.

---

## 👨‍💻 Töhfə Vermə

1. Fork et
2. Yeni branch aç: `feature/yenilik`
3. Dəyişiklik et və commit et
4. Push et: `git push origin feature/yenilik`
5. Pull Request yaradın

---

## 📄 Lisenziya

## Lisenziya

Bu layihə MIT lisenziyası ilə təmin olunub. Ətraflı məlumat üçün [LICENSE](LICENSE) faylına baxın.

---

## 📫 Əlaqə

- Müəllif: [Anar Qasımov](https://github.com/anargasimov1)
- E-poçt: anar.gasimov.89@gmail.com

---

> Bu sənəd avtomatik sənədləşmə (Swagger) və Docker mühiti ilə peşəkar backend sistemlərin qurulmasına misal olaraq tərtib edilmişdir.
