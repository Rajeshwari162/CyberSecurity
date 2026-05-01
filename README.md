# 🔐 Cybersecurity Algorithms Suite with CI/CD Pipeline

## 📖 Overview 
This project is a comprehensive implementation of classical cryptographic algorithms along with a fully automated CI/CD pipeline. It demonstrates both core cybersecurity concepts and modern software engineering practices using Java-based tools and DevOps integration.

## 🧩 Modules Implemented
- Monoalphabetic Cipher
- Polyalphabetic Cipher
- Polygraphic Cipher
- Transposition Cipher
- Application Module (Unified Driver Program)

## ⚙️ Key Features
- Encryption & Decryption for multiple cipher techniques
- Modular structure for each cryptographic algorithm
- Unified application interface to test all modules
- Automated build and test execution
- Continuous Integration pipeline setup

## 🛠️ Tech Stack
- Java
- JUnit (Unit Testing)
- Maven (Build & Dependency Management)
- Jenkins (CI/CD Pipeline)
- GitHub Webhooks

## 🔄 CI/CD Workflow
Code pushed to GitHub repository -> Webhook triggers Jenkins job automatically -> Maven builds the project -> JUnit test cases are executed -> Build status is updated in Jenkins dashboard
<img width="800" height="800" alt="ChatGPT Image May 1, 2026, 03_03_57 PM" src="https://github.com/user-attachments/assets/7fcf94ea-b276-45c5-96b6-5c750bc1c1d9" />


## 🧪 Testing
- Unit testing implemented using JUnit
- Each cipher module is tested for:
- Correct encryption output
- Correct decryption output
- Edge cases (invalid input handling)

## 📚 Learning Outcomes
- Understanding classical encryption techniques
- Implementation of modular Java architecture
- Hands-on experience with CI/CD pipelines
- Integration of GitHub + Jenkins automation
- Writing and executing unit test cases using JUnit

## ▶️ How to Run
- ```mvn clean install```
- ```java -jar target/your-app.jar```

## 🚀 Future Improvements
- Add GUI interface for cipher selection
- Add modern encryption algorithms (AES, RSA)
- Dockerize the application
- Deploy CI/CD pipeline to cloud (AWS/Jenkins server)

## 💡 Project Highlight
This project combines Cybersecurity fundamentals + DevOps automation, simulating a real-world software delivery pipeline.
