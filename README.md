# Bring Global exercise

## Overview

Welcome! This Jakarta EE 8 project utilizes JDK 1.8 and Docker for easy deployment. This README provides instructions on how to set up and run the project effortlessly.

## Prerequisites

Before getting started, ensure you have the following installed on your machine:
- [Docker](https://www.docker.com/get-started)

## Setup

1. Clone the repository to your local machine:

    ```bash
    git clone https://github.com/your-username/your-project.git
    ```

2. Navigate to the project directory:

    ```bash
    cd bring-global
    ```

3. Create a copy of the `env-example.txt` file and rename it to `.env`:

    ```bash
    cp env-example.txt .env
    ```

4. Open the `.env` file and modify the configurations as needed.

## Running the Project

Execute the following command in the terminal to start the project using Docker:

```bash
docker-compose up -d --build
```

## Endpoints
| Method | Description                    | URI                                                                                                                                  |
|-----|--------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| GET | Get all transactions           | [ http://localhost:80/transactions](http://localhost:80/transactions)                                                                |
| GET | Get transactions by type       | [ http://localhost:80/transactions/filter?type={transaction_type}](http://localhost:80//transactions/filter?type={transaction_type}) |
| GET | Get transaction amount by type | [ http://localhost:80/transactions/amount?type={transaction_type}](http://localhost:80/transactions/amount?type={transaction_type})  |
