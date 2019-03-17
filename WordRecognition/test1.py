import string

identiList = {}
keywords = {}

# 标识符
keywords['var'] = 700
# 整数
keywords['const'] = 400
# Error
keywords['Error'] = 500


def save(text):
    try:
        float(text)
        save_const(text)
    except ValueError:
        save_var(text)


def save_const(text):
    if text not in identiList.keys():
        identiList[text] = keywords['const']


def save_var(text):
    if text not in identiList.keys():
        if is_signal(text):
            identiList[text] = keywords['var']
        else:
            identiList[text] = keywords['Error']


def is_signal(s):
    if s[0] == '_' or s[0] in string.ascii_letters:
        for i in s:
            if i in string.ascii_letters or i == '_' or i in string.digits:
                pass
            else:
                return 0
        return True
    else:
        return False


# 单词识别
def recognition(filename):
    try:
        fp_read = open(filename, 'r')
        read = fp_read.read()
        save(read)

    except Exception as e:
        print(e)


def main():
    recognition('demo.txt')
    for i in identiList.keys():
        print("(", i, ",", identiList[i], ")")


if __name__ == '__main__':
    main()